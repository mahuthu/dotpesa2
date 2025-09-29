package com.example.smscallcapture.utils

import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.CallLog
import android.widget.Toast
import com.example.smscallcapture.SmsCallApplication
import com.example.smscallcapture.data.models.CallEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CallLogObserver(private val context: Context) : ContentObserver(Handler(Looper.getMainLooper())) {
    private val app get() = context.applicationContext as SmsCallApplication
    private val settings = SettingsManager(context)

    override fun onChange(selfChange: Boolean, uri: Uri?) {
        super.onChange(selfChange, uri)
        ingestNewCalls()
    }

    fun ingestNewCalls() {
        CoroutineScope(Dispatchers.IO).launch {
            val last = app.repository.getMaxCallStartTime() ?: 0L
            val cr = context.contentResolver
            val projection = arrayOf(
                CallLog.Calls.NUMBER,
                CallLog.Calls.TYPE,
                CallLog.Calls.DATE,
                CallLog.Calls.DURATION
            )
            val selection = "${CallLog.Calls.DATE}>?"
            val args = arrayOf(last.toString())
            val sort = "${CallLog.Calls.DATE} ASC"
            val now = System.currentTimeMillis()
            var imported = 0
            cr.query(CallLog.Calls.CONTENT_URI, projection, selection, args, sort)?.use { cursor ->
                val idxNumber = cursor.getColumnIndex(CallLog.Calls.NUMBER)
                val idxType = cursor.getColumnIndex(CallLog.Calls.TYPE)
                val idxDate = cursor.getColumnIndex(CallLog.Calls.DATE)
                val idxDuration = cursor.getColumnIndex(CallLog.Calls.DURATION)
                while (cursor.moveToNext()) {
                    val number = cursor.getString(idxNumber) ?: "Unknown"
                    val typeInt = cursor.getInt(idxType)
                    val start = cursor.getLong(idxDate)
                    val durationSec = cursor.getLong(idxDuration)
                    val end = if (durationSec > 0) start + (durationSec * 1000L) else null
                    val type = when (typeInt) {
                        CallLog.Calls.INCOMING_TYPE -> "INCOMING"
                        CallLog.Calls.OUTGOING_TYPE -> "OUTGOING"
                        CallLog.Calls.MISSED_TYPE -> "MISSED"
                        else -> "UNKNOWN"
                    }
                    val entity = CallEntity(
                        callerId = number,
                        callType = type,
                        startTime = start,
                        endTime = end,
                        uploadedDate = null,
                        status = "PENDING",
                        branchId = settings.getBranchId(),
                        deviceId = settings.getModemId(),
                        createdAt = now,
                        updatedAt = now
                    )
                    app.repository.insertCall(entity)
                    imported++
                }
            }
            if (imported > 0 && settings.isShowToastsEnabled()) {
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(context, "Imported $imported new calls", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

