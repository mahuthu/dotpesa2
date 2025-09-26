package com.example.smscallcapture

import android.app.Application
import com.example.smscallcapture.data.database.AppDatabase
import com.example.smscallcapture.data.repository.SmsCallRepository

class SmsCallApplication : Application() {
    lateinit var repository: SmsCallRepository
        private set

    override fun onCreate() {
        super.onCreate()
        val db = AppDatabase.getDatabase(this)
        repository = SmsCallRepository(db.smsDao(), db.callDao(), db.settingsDao())
    }
}




















