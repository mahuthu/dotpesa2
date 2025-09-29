package com.example.smscallcapture.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u001a\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/example/smscallcapture/utils/CallLogObserver;", "Landroid/database/ContentObserver;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "app", "Lcom/example/smscallcapture/SmsCallApplication;", "getApp", "()Lcom/example/smscallcapture/SmsCallApplication;", "settings", "Lcom/example/smscallcapture/utils/SettingsManager;", "ingestNewCalls", "", "onChange", "selfChange", "", "uri", "Landroid/net/Uri;", "app_debug"})
public final class CallLogObserver extends android.database.ContentObserver {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smscallcapture.utils.SettingsManager settings = null;
    
    public CallLogObserver(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    private final com.example.smscallcapture.SmsCallApplication getApp() {
        return null;
    }
    
    @java.lang.Override()
    public void onChange(boolean selfChange, @org.jetbrains.annotations.Nullable()
    android.net.Uri uri) {
    }
    
    public final void ingestNewCalls() {
    }
}