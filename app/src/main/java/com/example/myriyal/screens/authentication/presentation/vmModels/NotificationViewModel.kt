package com.example.myriyal.screens.authentication.presentation.vmModels

import androidx.lifecycle.ViewModel
import androidx.security.crypto.EncryptedSharedPreferences
import com.example.myriyal.screens.authentication.domain.useCases.GetFcmTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getFcmTokenUseCase: GetFcmTokenUseCase,
    private val encryptedPrefs: EncryptedSharedPreferences
) : ViewModel() {

    // Fetches the FCM token and stores it securely
    fun fetchFcmToken() {
        runBlocking {
            val token = getFcmTokenUseCase.execute()
            encryptedPrefs.edit().putString("fcm_token", token).apply()
        }
    }

    // Returns the stored FCM token if available
    fun getStoredFcmToken(): String? {
        return encryptedPrefs.getString("fcm_token", null)
    }
}
