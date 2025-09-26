package com.example.smscallcapture.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("upload/sms")
    suspend fun uploadSms(@Body smsUpload: SmsUploadRequest): Response<Unit>

    @POST("upload/call")
    suspend fun uploadCall(@Body callUpload: CallUploadRequest): Response<Unit>
}

data class SmsUploadRequest(
    val branchId: String,
    val modemId: String,
    val id: Long,
    val sender: String,
    val message: String,
    val receivedDate: Long
)

data class CallUploadRequest(
    val branchId: String,
    val modemId: String,
    val id: Long,
    val callerId: String,
    val callType: String,
    val startTime: Long,
    val endTime: Long?
)




















