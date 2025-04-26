package com.example.myriyal.screens.authentication.presentation.vmModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.security.crypto.EncryptedSharedPreferences

import com.example.myriyal.screens.authentication.domain.useCases.GetFcmTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getAuthTokenUseCase: GetFcmTokenUseCase,
    private val encryptedPrefs: EncryptedSharedPreferences
) : ViewModel() {

    // Fetches the FCM token and stores it securely
    fun fetchAuthToken() {
//        runBlocking {
//            Log.d("Token auth", "Before executing use case")
//            val token = getFcmTokenUseCase.execute()
//            Log.d("Token auth", "Fetched token: $token" )
//            encryptedPrefs.edit().putString("auth_token", token).apply()
//            val storedToken = encryptedPrefs.getString("auth_token", null)
//            println("Token auth Decrypted token: $storedToken")
//
//        }
//
//    }
        viewModelScope.launch {
            Log.d("Token auth", "Before executing use case")
            val token = getAuthTokenUseCase.execute()

            if (!token.isNullOrEmpty()) {
                encryptedPrefs.edit().putString("fcm_token", token).apply()
                val storedToken = encryptedPrefs.getString("fcm_token", null)
                Log.d("Token auth", " Stored + Decrypted token: $storedToken")
            } else {
                Log.e("Token auth", " Token is null or empty!")
            }
        }
    }


    // Returns the stored FCM token if available
    fun getStoredAuthToken(): String? {
        return encryptedPrefs.getString("fcm_token", null)
    }
}
