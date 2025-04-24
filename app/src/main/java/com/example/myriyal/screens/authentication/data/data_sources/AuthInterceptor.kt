package com.example.myriyal.screens.authentication.data.data_sources

import androidx.security.crypto.EncryptedSharedPreferences
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor(
    private val encryptedPrefs: EncryptedSharedPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // Securely fetch token from EncryptedSharedPreferences
        val token = encryptedPrefs.getString("fcm_token", null)

        // Add token to Authorization header if it exists
        if (!token.isNullOrEmpty()) {
            println("Token12: $token")
            requestBuilder.addHeader("authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }
}
