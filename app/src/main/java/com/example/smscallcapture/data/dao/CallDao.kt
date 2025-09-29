package com.example.smscallcapture.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.example.smscallcapture.data.models.CallEntity

@Dao
interface CallDao {
    @Query("SELECT * FROM calls ORDER BY startTime DESC")
    fun getAllCalls(): Flow<List<CallEntity>>

    @Query("SELECT * FROM calls WHERE status = :status ORDER BY startTime DESC")
    fun getCallsByStatus(status: String): Flow<List<CallEntity>>

    @Query("SELECT * FROM calls WHERE status IN ('PENDING', 'FAILED')")
    suspend fun getPendingAndFailedCalls(): List<CallEntity>

    @Insert
    suspend fun insertCall(call: CallEntity): Long

    @Update
    suspend fun updateCall(call: CallEntity)

    @Query("UPDATE calls SET status = :status, uploadedDate = :uploadedDate, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateCallStatus(id: Long, status: String, uploadedDate: Long?, updatedAt: Long)

    @Query("SELECT MAX(startTime) FROM calls")
    suspend fun getMaxStartTime(): Long?
}




















