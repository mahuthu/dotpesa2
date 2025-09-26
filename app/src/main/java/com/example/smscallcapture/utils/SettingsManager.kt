package com.example.smscallcapture.utils

import android.content.Context
import android.content.SharedPreferences

class SettingsManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)
    
    companion object {
        const val KEY_PROTOCOL = "protocol"
        const val KEY_HOST = "host"
        const val KEY_PORT = "port"
        const val KEY_BASE_URL = "base_url"
        const val KEY_BRANCH_ID = "branch_id"
        const val KEY_MODEM_ID = "modem_id"
        const val KEY_SYNC_INTERVAL = "sync_interval_seconds"
        const val KEY_SHOW_TOASTS = "show_toasts"
        const val KEY_SMS_SOUND_RECEIVE = "sms_sound_receive"
        const val KEY_SMS_SOUND_UPLOAD = "sms_sound_upload"
    }
    
    fun setProtocol(protocol: String) = prefs.edit().putString(KEY_PROTOCOL, protocol).apply()
    fun getProtocol(): String = prefs.getString(KEY_PROTOCOL, "https") ?: "https"
    
    fun setHost(host: String) = prefs.edit().putString(KEY_HOST, host).apply()
    fun getHost(): String = prefs.getString(KEY_HOST, "") ?: ""
    
    fun setPort(port: String) = prefs.edit().putString(KEY_PORT, port).apply()
    fun getPort(): String = prefs.getString(KEY_PORT, "") ?: ""
    
    fun setBaseUrl(baseUrl: String) = prefs.edit().putString(KEY_BASE_URL, baseUrl).apply()
    fun getBaseUrl(): String = prefs.getString(KEY_BASE_URL, "") ?: ""
    
    fun setBranchId(branchId: String) = prefs.edit().putString(KEY_BRANCH_ID, branchId).apply()
    fun getBranchId(): String = prefs.getString(KEY_BRANCH_ID, "default_branch") ?: "default_branch"
    
    fun setModemId(modemId: String) = prefs.edit().putString(KEY_MODEM_ID, modemId).apply()
    fun getModemId(): String = prefs.getString(KEY_MODEM_ID, "default_modem") ?: "default_modem"
    
    fun setSyncInterval(interval: Int) = prefs.edit().putInt(KEY_SYNC_INTERVAL, interval).apply()
    fun getSyncInterval(): Int = prefs.getInt(KEY_SYNC_INTERVAL, 300)
    
    fun setShowToasts(show: Boolean) = prefs.edit().putBoolean(KEY_SHOW_TOASTS, show).apply()
    fun isShowToastsEnabled(): Boolean = prefs.getBoolean(KEY_SHOW_TOASTS, true)
    
    fun setSmsReceiveSound(enabled: Boolean) = prefs.edit().putBoolean(KEY_SMS_SOUND_RECEIVE, enabled).apply()
    fun isSmsReceiveSoundEnabled(): Boolean = prefs.getBoolean(KEY_SMS_SOUND_RECEIVE, true)
    
    fun setSmsUploadSound(enabled: Boolean) = prefs.edit().putBoolean(KEY_SMS_SOUND_UPLOAD, enabled).apply()
    fun isSmsUploadSoundEnabled(): Boolean = prefs.getBoolean(KEY_SMS_SOUND_UPLOAD, true)
    
    fun buildBaseUrl(): String {
        val protocol = getProtocol()
        val host = getHost()
        val port = getPort()
        
        if (host.isEmpty()) return ""
        
        return if (port.isEmpty() || 
                   (protocol == "https" && port == "443") || 
                   (protocol == "http" && port == "80")) {
            "$protocol://$host/"
        } else {
            "$protocol://$host:$port/"
        }
    }
    
    fun updateBaseUrl() {
        val baseUrl = buildBaseUrl()
        setBaseUrl(baseUrl)
    }
}




















