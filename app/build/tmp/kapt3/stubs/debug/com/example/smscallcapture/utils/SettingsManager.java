package com.example.smscallcapture.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0015\u0018\u0000 *2\u00020\u0001:\u0001*B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\u0006\u0010\n\u001a\u00020\bJ\u0006\u0010\u000b\u001a\u00020\bJ\u0006\u0010\f\u001a\u00020\bJ\u0006\u0010\r\u001a\u00020\bJ\u0006\u0010\u000e\u001a\u00020\bJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0012J\u0006\u0010\u0014\u001a\u00020\u0012J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\bJ\u000e\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\bJ\u000e\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\bJ\u000e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\bJ\u000e\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\bJ\u000e\u0010 \u001a\u00020\u00162\u0006\u0010!\u001a\u00020\bJ\u000e\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\u0012J\u000e\u0010$\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u0012J\u000e\u0010&\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u0012J\u000e\u0010\'\u001a\u00020\u00162\u0006\u0010(\u001a\u00020\u0010J\u0006\u0010)\u001a\u00020\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lcom/example/smscallcapture/utils/SettingsManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "buildBaseUrl", "", "getBaseUrl", "getBranchId", "getHost", "getModemId", "getPort", "getProtocol", "getSyncInterval", "", "isShowToastsEnabled", "", "isSmsReceiveSoundEnabled", "isSmsUploadSoundEnabled", "setBaseUrl", "", "baseUrl", "setBranchId", "branchId", "setHost", "host", "setModemId", "modemId", "setPort", "port", "setProtocol", "protocol", "setShowToasts", "show", "setSmsReceiveSound", "enabled", "setSmsUploadSound", "setSyncInterval", "interval", "updateBaseUrl", "Companion", "app_debug"})
public final class SettingsManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_PROTOCOL = "protocol";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_HOST = "host";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_PORT = "port";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_BASE_URL = "base_url";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_BRANCH_ID = "branch_id";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_MODEM_ID = "modem_id";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_SYNC_INTERVAL = "sync_interval_seconds";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_SHOW_TOASTS = "show_toasts";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_SMS_SOUND_RECEIVE = "sms_sound_receive";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_SMS_SOUND_UPLOAD = "sms_sound_upload";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.smscallcapture.utils.SettingsManager.Companion Companion = null;
    
    public SettingsManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final void setProtocol(@org.jetbrains.annotations.NotNull()
    java.lang.String protocol) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getProtocol() {
        return null;
    }
    
    public final void setHost(@org.jetbrains.annotations.NotNull()
    java.lang.String host) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHost() {
        return null;
    }
    
    public final void setPort(@org.jetbrains.annotations.NotNull()
    java.lang.String port) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPort() {
        return null;
    }
    
    public final void setBaseUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String baseUrl) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBaseUrl() {
        return null;
    }
    
    public final void setBranchId(@org.jetbrains.annotations.NotNull()
    java.lang.String branchId) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBranchId() {
        return null;
    }
    
    public final void setModemId(@org.jetbrains.annotations.NotNull()
    java.lang.String modemId) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getModemId() {
        return null;
    }
    
    public final void setSyncInterval(int interval) {
    }
    
    public final int getSyncInterval() {
        return 0;
    }
    
    public final void setShowToasts(boolean show) {
    }
    
    public final boolean isShowToastsEnabled() {
        return false;
    }
    
    public final void setSmsReceiveSound(boolean enabled) {
    }
    
    public final boolean isSmsReceiveSoundEnabled() {
        return false;
    }
    
    public final void setSmsUploadSound(boolean enabled) {
    }
    
    public final boolean isSmsUploadSoundEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String buildBaseUrl() {
        return null;
    }
    
    public final void updateBaseUrl() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/example/smscallcapture/utils/SettingsManager$Companion;", "", "()V", "KEY_BASE_URL", "", "KEY_BRANCH_ID", "KEY_HOST", "KEY_MODEM_ID", "KEY_PORT", "KEY_PROTOCOL", "KEY_SHOW_TOASTS", "KEY_SMS_SOUND_RECEIVE", "KEY_SMS_SOUND_UPLOAD", "KEY_SYNC_INTERVAL", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}