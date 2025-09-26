package com.example.smscallcapture.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.example.smscallcapture.data.models.SmsEntity

@Dao
interface SmsDao {
    @Query("SELECT * FROM sms ORDER BY receivedDate DESC")
    fun getAllSms(): Flow<List<SmsEntity>>

    @Query("SELECT * FROM sms WHERE status = :status ORDER BY receivedDate DESC")
    fun getSmsByStatus(status: String): Flow<List<SmsEntity>>

    @Query("SELECT * FROM sms WHERE status IN ('PENDING', 'FAILED')")
    suspend fun getPendingAndFailedSms(): List<SmsEntity>

    @Insert
    suspend fun insertSms(sms: SmsEntity): Long

    @Update
    suspend fun updateSms(sms: SmsEntity)

    @Query("UPDATE sms SET status = :status, uploadedDate = :uploadedDate, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateSmsStatus(id: Long, status: String, uploadedDate: Long?, updatedAt: Long)
}




















