package com.example.smscallcapture.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.example.smscallcapture.SmsCallApplication
import com.example.smscallcapture.data.models.SmsEntity

class SmsViewModel(app: Application) : AndroidViewModel(app) {
    private val repository = (app as SmsCallApplication).repository
    private val _statusFilter = MutableLiveData<String>("ALL")

    val sms: LiveData<List<SmsEntity>> = _statusFilter.switchMap { status ->
        if (status == "ALL") repository.getAllSms().asLiveData() else repository.getSmsByStatus(status).asLiveData()
    }

    fun setStatusFilter(status: String) { _statusFilter.value = status }
}






