package com.example.smscallcapture.data.repository

import com.example.smscallcapture.data.dao.*
import com.example.smscallcapture.data.models.*
import kotlinx.coroutines.flow.Flow

class SmsCallRepository(
    private val smsDao: SmsDao,
    private val callDao: CallDao,
    private val settingsDao: SettingsDao
) {
    fun getAllSms(): Flow<List<SmsEntity>> = smsDao.getAllSms()
    fun getSmsByStatus(status: String): Flow<List<SmsEntity>> = smsDao.getSmsByStatus(status)
    suspend fun insertSms(sms: SmsEntity) = smsDao.insertSms(sms)
    suspend fun updateSmsStatus(id: Long, status: String, uploadedDate: Long?, updatedAt: Long) =
        smsDao.updateSmsStatus(id, status, uploadedDate, updatedAt)
    suspend fun getPendingAndFailedSms() = smsDao.getPendingAndFailedSms()

    fun getAllCalls(): Flow<List<CallEntity>> = callDao.getAllCalls()
    fun getCallsByStatus(status: String): Flow<List<CallEntity>> = callDao.getCallsByStatus(status)
    suspend fun insertCall(call: CallEntity) = callDao.insertCall(call)
    suspend fun updateCallStatus(id: Long, status: String, uploadedDate: Long?, updatedAt: Long) =
        callDao.updateCallStatus(id, status, uploadedDate, updatedAt)
    suspend fun getPendingAndFailedCalls() = callDao.getPendingAndFailedCalls()
    suspend fun getMaxCallStartTime(): Long? = callDao.getMaxStartTime()

    suspend fun getSetting(key: String) = settingsDao.getSetting(key)
    suspend fun setSetting(key: String, value: String) = settingsDao.insertSetting(SettingsEntity(key, value))
    suspend fun getAllSettings() = settingsDao.getAllSettings()
}




















