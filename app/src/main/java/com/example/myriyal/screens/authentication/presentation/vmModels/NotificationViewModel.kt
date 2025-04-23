package com.example.myriyal.screens.authentication.presentation.vmModels


import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.myriyal.screens.authentication.domain.useCases.GetFcmTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getFcmTokenUseCase: GetFcmTokenUseCase
) : ViewModel() {

    fun fetchFcmToken() {
        getFcmTokenUseCase.execute().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.d("FCM", "FCM Token: $token")
                // TODO: send token to server if needed
            } else {
                Log.e("FCM", "Failed to get FCM token", task.exception)
            }
        }
    }
}
