package com.example.smscallcapture.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(
    tableName = "calls",
    indices = [
        Index(value = ["status"]),
        Index(value = ["startTime"]),
        Index(value = ["updatedAt"])   
    ]
)
data class CallEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val callerId: String,
    val callType: String,
    val startTime: Long,
    val endTime: Long?,
    val uploadedDate: Long? = null,
    val status: String,
    val branchId: String,
    val deviceId: String,
    val createdAt: Long,
    val updatedAt: Long
)




















