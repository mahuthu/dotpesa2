package com.example.smscallcapture.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.provider.Telephony
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.smscallcapture.SmsCallApplication
import com.example.smscallcapture.data.models.SmsEntity
import com.example.smscallcapture.utils.SettingsManager

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION == intent.action) {
            val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
            val app = context.applicationContext as SmsCallApplication
            val settingsManager = SettingsManager(context)
            
            CoroutineScope(Dispatchers.IO).launch {
                val branchId = settingsManager.getBranchId()
                val deviceId = settingsManager.getModemId()
                val currentTime = System.currentTimeMillis()
                
                messages?.forEach { message ->
                    val smsEntity = SmsEntity(
                        sender = message.originatingAddress ?: "Unknown",
                        message = message.messageBody ?: "",
                        receivedDate = message.timestampMillis,
                        status = "PENDING",
                        branchId = branchId,
                        deviceId = deviceId,
                        createdAt = currentTime,
                        updatedAt = currentTime
                    )
                    
                    app.repository.insertSms(smsEntity)
                    Log.d("SmsReceiver", "SMS inserted: ${smsEntity.sender}")
                    
                    if (settingsManager.isSmsReceiveSoundEnabled()) {
                        playSound(context, "ting.wav")
                    }
                }
            }
        }
    }
    
    private fun playSound(context: Context, fileName: String) {
        try {
            val assetManager = context.assets
            val afd = assetManager.openFd(fileName)
            val mediaPlayer = MediaPlayer().apply {
                setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
                prepareAsync()
                setOnPreparedListener { start() }
                setOnCompletionListener { release() }
            }
        } catch (e: Exception) {
            Log.e("SmsReceiver", "Error playing sound", e)
        }
    }
}




















