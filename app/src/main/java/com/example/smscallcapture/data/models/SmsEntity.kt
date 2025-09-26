package com.example.smscallcapture.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(
    tableName = "sms",
    indices = [
        Index(value = ["status"]),
        Index(value = ["receivedDate"]),
        Index(value = ["updatedAt"])
    ]
)
data class SmsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val sender: String,
    val message: String,
    val receivedDate: Long,
    val uploadedDate: Long? = null,
    val status: String,
    val branchId: String,
    val deviceId: String,
    val createdAt: Long,
    val updatedAt: Long
)




















