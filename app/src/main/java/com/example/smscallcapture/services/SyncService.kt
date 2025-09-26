package com.example.smscallcapture.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.smscallcapture.R
import com.example.smscallcapture.SmsCallApplication
import com.example.smscallcapture.network.CallUploadRequest
import com.example.smscallcapture.network.NetworkManager
import com.example.smscallcapture.network.SmsUploadRequest
import com.example.smscallcapture.utils.SettingsManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SyncService : Service() {
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var settings: SettingsManager
    private lateinit var networkManager: NetworkManager

    override fun onCreate() {
        super.onCreate()
        settings = SettingsManager(this)
        networkManager = NetworkManager()
        createNotificationChannel()
        startForeground(NOTIF_ID, buildNotification("Sync running"))
        scheduleNextRun()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val target = intent?.getStringExtra(EXTRA_TARGET) ?: TARGET_ALL
        runSync(target)
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }

    private fun runSync(target: String = TARGET_ALL) {
        val app = application as SmsCallApplication
        val baseUrl = settings.getBaseUrl()
        if (baseUrl.isEmpty()) {
            scheduleNextRun()
            return
        }
        val api = networkManager.getApiService(baseUrl)
        val branchId = settings.getBranchId()
        val modemId = settings.getModemId()
        val now = System.currentTimeMillis()

        CoroutineScope(Dispatchers.IO).launch {
            var uploadedAny = false
            try {
                if (target == TARGET_ALL || target == TARGET_SMS) {
                    val pendingSms = app.repository.getPendingAndFailedSms()
                    for (sms in pendingSms) {
                        val request = SmsUploadRequest(branchId, modemId, sms.id, sms.sender, sms.message, sms.receivedDate)
                        try {
                            val resp = api.uploadSms(request)
                            if (resp.isSuccessful) {
                                app.repository.updateSmsStatus(sms.id, "UPLOADED", now, now)
                                uploadedAny = true
                            } else {
                                app.repository.updateSmsStatus(sms.id, "FAILED", sms.uploadedDate, now)
                            }
                        } catch (_: Exception) {
                            app.repository.updateSmsStatus(sms.id, "FAILED", sms.uploadedDate, now)
                        }
                    }
                }

                if (target == TARGET_ALL || target == TARGET_CALLS) {
                    val pendingCalls = app.repository.getPendingAndFailedCalls()
                    for (call in pendingCalls) {
                        val request = CallUploadRequest(branchId, modemId, call.id, call.callerId, call.callType, call.startTime, call.endTime)
                        try {
                            val resp = api.uploadCall(request)
                            if (resp.isSuccessful) {
                                app.repository.updateCallStatus(call.id, "UPLOADED", now, now)
                                uploadedAny = true
                            } else {
                                app.repository.updateCallStatus(call.id, "FAILED", call.uploadedDate, now)
                            }
                        } catch (_: Exception) {
                            app.repository.updateCallStatus(call.id, "FAILED", call.uploadedDate, now)
                        }
                    }
                }
            } finally {
                if (uploadedAny) {
                    if (settings.isSmsUploadSoundEnabled()) playSound("tung.wav")
                    if (settings.isShowToastsEnabled()) Toast.makeText(this@SyncService, "Upload complete", Toast.LENGTH_SHORT).show()
                }
                scheduleNextRun()
            }
        }
    }

    private fun playSound(fileName: String) {
        try {
            val afd = assets.openFd(fileName)
            MediaPlayer().apply {
                setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                prepareAsync()
                setOnPreparedListener { start() }
                setOnCompletionListener { release() }
            }
        } catch (_: Exception) { }
    }

    private fun scheduleNextRun() {
        val intervalMs = (settings.getSyncInterval().coerceAtLeast(15)) * 1000L
        handler.postDelayed({ runSync() }, intervalMs)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "Sync", NotificationManager.IMPORTANCE_LOW)
            val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.createNotificationChannel(channel)
        }
    }

    private fun buildNotification(text: String): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(text)
            .setSmallIcon(android.R.drawable.stat_notify_sync)
            .setOngoing(true)
            .build()
    }

    companion object {
        private const val CHANNEL_ID = "sync_channel"
        private const val NOTIF_ID = 1001
        const val EXTRA_TARGET = "extra_target"
        const val TARGET_ALL = "ALL"
        const val TARGET_SMS = "SMS"
        const val TARGET_CALLS = "CALLS"
        
        fun startSync(context: Context, target: String = TARGET_ALL) {
            val i = Intent(context, SyncService::class.java)
            i.putExtra(EXTRA_TARGET, target)
            context.startForegroundService(i)
        }
    }
}
