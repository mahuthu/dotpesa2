package com.example.smscallcapture.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.example.smscallcapture.SmsCallApplication
import com.example.smscallcapture.data.models.CallEntity

class CallViewModel(app: Application) : AndroidViewModel(app) {
    private val repository = (app as SmsCallApplication).repository
    private val _statusFilter = MutableLiveData<String>("ALL")

    val calls: LiveData<List<CallEntity>> = _statusFilter.switchMap { status ->
        if (status == "ALL") repository.getAllCalls().asLiveData() else repository.getCallsByStatus(status).asLiveData()
    }

    fun setStatusFilter(status: String) { _statusFilter.value = status }
}






