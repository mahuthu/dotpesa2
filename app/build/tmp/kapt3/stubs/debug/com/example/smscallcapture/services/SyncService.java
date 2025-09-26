package com.example.smscallcapture.services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0014\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u000eH\u0016J\b\u0010\u0014\u001a\u00020\u000eH\u0016J\"\u0010\u0015\u001a\u00020\u00162\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0016J\u0010\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\fH\u0002J\u0012\u0010\u001b\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\fH\u0002J\b\u0010\u001d\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/example/smscallcapture/services/SyncService;", "Landroid/app/Service;", "()V", "handler", "Landroid/os/Handler;", "networkManager", "Lcom/example/smscallcapture/network/NetworkManager;", "settings", "Lcom/example/smscallcapture/utils/SettingsManager;", "buildNotification", "Landroid/app/Notification;", "text", "", "createNotificationChannel", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "playSound", "fileName", "runSync", "target", "scheduleNextRun", "Companion", "app_debug"})
public final class SyncService extends android.app.Service {
    @org.jetbrains.annotations.NotNull()
    private final android.os.Handler handler = null;
    private com.example.smscallcapture.utils.SettingsManager settings;
    private com.example.smscallcapture.network.NetworkManager networkManager;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String CHANNEL_ID = "sync_channel";
    private static final int NOTIF_ID = 1001;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_TARGET = "extra_target";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TARGET_ALL = "ALL";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TARGET_SMS = "SMS";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TARGET_CALLS = "CALLS";
    @org.jetbrains.annotations.NotNull()
    public static final com.example.smscallcapture.services.SyncService.Companion Companion = null;
    
    public SyncService() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    private final void runSync(java.lang.String target) {
    }
    
    private final void playSound(java.lang.String fileName) {
    }
    
    private final void scheduleNextRun() {
    }
    
    private final void createNotificationChannel() {
    }
    
    private final android.app.Notification buildNotification(java.lang.String text) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/example/smscallcapture/services/SyncService$Companion;", "", "()V", "CHANNEL_ID", "", "EXTRA_TARGET", "NOTIF_ID", "", "TARGET_ALL", "TARGET_CALLS", "TARGET_SMS", "startSync", "", "context", "Landroid/content/Context;", "target", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void startSync(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        java.lang.String target) {
        }
    }
}