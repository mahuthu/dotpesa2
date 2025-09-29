package com.example.smscallcapture

import android.app.Application
import android.Manifest
import android.content.pm.PackageManager
import android.provider.CallLog
import androidx.core.content.ContextCompat
import com.example.smscallcapture.data.database.AppDatabase
import com.example.smscallcapture.data.repository.SmsCallRepository
import com.example.smscallcapture.utils.CallLogObserver

class SmsCallApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { SmsCallRepository(database.smsDao(), database.callDao(), database.settingsDao()) }
    private var callLogObserver: CallLogObserver? = null

    override fun onCreate() {
        super.onCreate()
        ensureCallObserverRegistered()
    }

    fun ensureCallObserverRegistered() {
        val granted = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED
        if (granted && callLogObserver == null) {
            val observer = CallLogObserver(this)
            contentResolver.registerContentObserver(CallLog.Calls.CONTENT_URI, true, observer)
            observer.ingestNewCalls()
            callLogObserver = observer
        }
    }
}




















