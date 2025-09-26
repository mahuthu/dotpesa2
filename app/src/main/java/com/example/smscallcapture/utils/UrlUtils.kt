package com.example.smscallcapture.utils

import android.util.Patterns
import java.net.URI

object UrlUtils {
    data class ParsedUrl(
        val protocol: String,
        val host: String,
        val port: String,
        val baseUrl: String
    )

    fun parseServerUrl(input: String): ParsedUrl? {
        val trimmed = input.trim()
        val uri = try { URI(trimmed) } catch (e: Exception) { return null }
        val scheme = uri.scheme?.lowercase() ?: return null
        if (scheme != "http" && scheme != "https") return null
        val host = uri.host ?: return null
        if (!isValidHost(host)) return null
        val port = when {
            uri.port in 1..65535 -> uri.port.toString()
            uri.port == -1 && scheme == "https" -> "443"
            uri.port == -1 && scheme == "http" -> "80"
            else -> return null
        }
        val base = buildBaseUrl(scheme, host, port)
        return ParsedUrl(scheme, host, port, base)
    }

    fun buildBaseUrl(protocol: String, host: String, port: String): String {
        return if ((protocol == "https" && port == "443") || (protocol == "http" && port == "80")) {
            "$protocol://$host/"
        } else {
            "$protocol://$host:$port/"
        }
    }

    fun isValidHost(host: String): Boolean {
        return Patterns.DOMAIN_NAME.matcher(host).matches() || Patterns.IP_ADDRESS.matcher(host).matches() || host.contains(":")
    }

    fun isValidPort(port: String): Boolean {
        val n = port.toIntOrNull() ?: return false
        return n in 1..65535
    }
}




















