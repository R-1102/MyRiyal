package com.example.myriyal.screens.authentication.presentation.vmModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myriyal.screens.authentication.domain.useCases.ForgotPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordVM @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : ViewModel() {

    private val eventsChannel = Channel<AllEvents>()
    val allEventsFlow = eventsChannel.receiveAsFlow()

    var email by mutableStateOf("")

    fun sendPasswordResetEmail(email: String) = viewModelScope.launch {
        try {
            val result = forgotPasswordUseCase.resetPassword(email)
            if (result.isSuccess) {
                eventsChannel.send(AllEvents.Message(result.getOrNull() ?: "Email sent successfully"))
            } else {
                eventsChannel.send(AllEvents.Error(result.exceptionOrNull()?.localizedMessage ?: "Failed to send email"))
            }
        } catch (e: Exception) {
            eventsChannel.send(AllEvents.Error("An error occurred: ${e.localizedMessage}"))
        }
    }

    sealed class AllEvents {
        data class Message(val message: String) : AllEvents()
        data class Error(val error: String) : AllEvents()
    }
}
