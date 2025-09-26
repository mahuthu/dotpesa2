package com.example.smscallcapture.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/example/smscallcapture/network/ApiService;", "", "uploadCall", "Lretrofit2/Response;", "", "callUpload", "Lcom/example/smscallcapture/network/CallUploadRequest;", "(Lcom/example/smscallcapture/network/CallUploadRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadSms", "smsUpload", "Lcom/example/smscallcapture/network/SmsUploadRequest;", "(Lcom/example/smscallcapture/network/SmsUploadRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ApiService {
    
    @retrofit2.http.POST(value = "upload/sms")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object uploadSms(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.smscallcapture.network.SmsUploadRequest smsUpload, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kotlin.Unit>> $completion);
    
    @retrofit2.http.POST(value = "upload/call")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object uploadCall(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.smscallcapture.network.CallUploadRequest callUpload, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<kotlin.Unit>> $completion);
}