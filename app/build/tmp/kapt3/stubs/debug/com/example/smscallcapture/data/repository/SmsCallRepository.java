package com.example.smscallcapture.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0012\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u000b0\nJ\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\u0013\u001a\u00020\u0014J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u000bH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0018\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0019J\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u000b0\n2\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u0016\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010!J\u001e\u0010\"\u001a\u00020#2\u0006\u0010\u0018\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010%J0\u0010&\u001a\u00020#2\u0006\u0010\'\u001a\u00020\u001c2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010(\u001a\u0004\u0018\u00010\u001c2\u0006\u0010)\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0002\u0010*J0\u0010+\u001a\u00020#2\u0006\u0010\'\u001a\u00020\u001c2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010(\u001a\u0004\u0018\u00010\u001c2\u0006\u0010)\u001a\u00020\u001cH\u0086@\u00a2\u0006\u0002\u0010*R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/example/smscallcapture/data/repository/SmsCallRepository;", "", "smsDao", "Lcom/example/smscallcapture/data/dao/SmsDao;", "callDao", "Lcom/example/smscallcapture/data/dao/CallDao;", "settingsDao", "Lcom/example/smscallcapture/data/dao/SettingsDao;", "(Lcom/example/smscallcapture/data/dao/SmsDao;Lcom/example/smscallcapture/data/dao/CallDao;Lcom/example/smscallcapture/data/dao/SettingsDao;)V", "getAllCalls", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/smscallcapture/data/models/CallEntity;", "getAllSettings", "Lcom/example/smscallcapture/data/models/SettingsEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllSms", "Lcom/example/smscallcapture/data/models/SmsEntity;", "getCallsByStatus", "status", "", "getPendingAndFailedCalls", "getPendingAndFailedSms", "getSetting", "key", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSmsByStatus", "insertCall", "", "call", "(Lcom/example/smscallcapture/data/models/CallEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertSms", "sms", "(Lcom/example/smscallcapture/data/models/SmsEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setSetting", "", "value", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCallStatus", "id", "uploadedDate", "updatedAt", "(JLjava/lang/String;Ljava/lang/Long;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSmsStatus", "app_debug"})
public final class SmsCallRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.smscallcapture.data.dao.SmsDao smsDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smscallcapture.data.dao.CallDao callDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smscallcapture.data.dao.SettingsDao settingsDao = null;
    
    public SmsCallRepository(@org.jetbrains.annotations.NotNull()
    com.example.smscallcapture.data.dao.SmsDao smsDao, @org.jetbrains.annotations.NotNull()
    com.example.smscallcapture.data.dao.CallDao callDao, @org.jetbrains.annotations.NotNull()
    com.example.smscallcapture.data.dao.SettingsDao settingsDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.smscallcapture.data.models.SmsEntity>> getAllSms() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.smscallcapture.data.models.SmsEntity>> getSmsByStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String status) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertSms(@org.jetbrains.annotations.NotNull()
    com.example.smscallcapture.data.models.SmsEntity sms, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateSmsStatus(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.Nullable()
    java.lang.Long uploadedDate, long updatedAt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getPendingAndFailedSms(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.smscallcapture.data.models.SmsEntity>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.smscallcapture.data.models.CallEntity>> getAllCalls() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.smscallcapture.data.models.CallEntity>> getCallsByStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String status) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertCall(@org.jetbrains.annotations.NotNull()
    com.example.smscallcapture.data.models.CallEntity call, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateCallStatus(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.Nullable()
    java.lang.Long uploadedDate, long updatedAt, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getPendingAndFailedCalls(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.smscallcapture.data.models.CallEntity>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getSetting(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.smscallcapture.data.models.SettingsEntity> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setSetting(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllSettings(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.smscallcapture.data.models.SettingsEntity>> $completion) {
        return null;
    }
}