package com.example.smscallcapture.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u000eB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0004J\u000e\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u0004J\u0010\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u0004\u00a8\u0006\u000f"}, d2 = {"Lcom/example/smscallcapture/utils/UrlUtils;", "", "()V", "buildBaseUrl", "", "protocol", "host", "port", "isValidHost", "", "isValidPort", "parseServerUrl", "Lcom/example/smscallcapture/utils/UrlUtils$ParsedUrl;", "input", "ParsedUrl", "app_debug"})
public final class UrlUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.smscallcapture.utils.UrlUtils INSTANCE = null;
    
    private UrlUtils() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.smscallcapture.utils.UrlUtils.ParsedUrl parseServerUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String input) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String buildBaseUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String protocol, @org.jetbrains.annotations.NotNull()
    java.lang.String host, @org.jetbrains.annotations.NotNull()
    java.lang.String port) {
        return null;
    }
    
    public final boolean isValidHost(@org.jetbrains.annotations.NotNull()
    java.lang.String host) {
        return false;
    }
    
    public final boolean isValidPort(@org.jetbrains.annotations.NotNull()
    java.lang.String port) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0003H\u00c6\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0016H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t\u00a8\u0006\u0018"}, d2 = {"Lcom/example/smscallcapture/utils/UrlUtils$ParsedUrl;", "", "protocol", "", "host", "port", "baseUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBaseUrl", "()Ljava/lang/String;", "getHost", "getPort", "getProtocol", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
    public static final class ParsedUrl {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String protocol = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String host = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String port = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String baseUrl = null;
        
        public ParsedUrl(@org.jetbrains.annotations.NotNull()
        java.lang.String protocol, @org.jetbrains.annotations.NotNull()
        java.lang.String host, @org.jetbrains.annotations.NotNull()
        java.lang.String port, @org.jetbrains.annotations.NotNull()
        java.lang.String baseUrl) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getProtocol() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getHost() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getPort() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getBaseUrl() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.example.smscallcapture.utils.UrlUtils.ParsedUrl copy(@org.jetbrains.annotations.NotNull()
        java.lang.String protocol, @org.jetbrains.annotations.NotNull()
        java.lang.String host, @org.jetbrains.annotations.NotNull()
        java.lang.String port, @org.jetbrains.annotations.NotNull()
        java.lang.String baseUrl) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}