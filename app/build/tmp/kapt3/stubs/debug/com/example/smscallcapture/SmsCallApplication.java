package com.example.smscallcapture;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0012\u001a\u00020\u0011H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0013"}, d2 = {"Lcom/example/smscallcapture/SmsCallApplication;", "Landroid/app/Application;", "()V", "callLogObserver", "Lcom/example/smscallcapture/utils/CallLogObserver;", "database", "Lcom/example/smscallcapture/data/database/AppDatabase;", "getDatabase", "()Lcom/example/smscallcapture/data/database/AppDatabase;", "database$delegate", "Lkotlin/Lazy;", "repository", "Lcom/example/smscallcapture/data/repository/SmsCallRepository;", "getRepository", "()Lcom/example/smscallcapture/data/repository/SmsCallRepository;", "repository$delegate", "ensureCallObserverRegistered", "", "onCreate", "app_debug"})
public final class SmsCallApplication extends android.app.Application {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy database$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy repository$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private com.example.smscallcapture.utils.CallLogObserver callLogObserver;
    
    public SmsCallApplication() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.smscallcapture.data.database.AppDatabase getDatabase() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.smscallcapture.data.repository.SmsCallRepository getRepository() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    public final void ensureCallObserverRegistered() {
    }
}