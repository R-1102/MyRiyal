package com.example.myriyal.screens.authentication.presentation.vmModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import com.example.myriyal.screens.authentication.domain.useCases.GetFcmTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getFcmTokenUseCase: GetFcmTokenUseCase,
    private val encryptedPrefs: EncryptedSharedPreferences
) : ViewModel() {

    // Fetches the FCM token and stores it securely
    fun fetchFcmToken() {
        getFcmTokenUseCase.execute().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.d("FCM", "FCM Token: $token")
                encryptedPrefs.edit().putString("fcm_token", token).apply()
            } else {
                Log.e("FCM", "Failed to get FCM token", task.exception)
            }
        }
    }

    // Returns the stored FCM token if available
    fun getStoredFcmToken(): String? {
        return encryptedPrefs.getString("fcm_token", null)
    }
}
