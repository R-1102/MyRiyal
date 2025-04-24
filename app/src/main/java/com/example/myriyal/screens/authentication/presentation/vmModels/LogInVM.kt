package com.example.myriyal.screens.authentication.presentation.vmModels

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myriyal.screens.authentication.domain.useCases.LogInUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInVM @Inject constructor(
    private val logInUseCase: LogInUseCase
) : ViewModel() {

    // State holders for user input
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    private val _firebaseUser = mutableStateOf<FirebaseUser?>(null)
    val firebaseUser: State<FirebaseUser?> = _firebaseUser

    // Events channel to communicate with UI
    private val eventsChannel = Channel<AllEvents>()
    val allEventsFlow = eventsChannel.receiveAsFlow()

    // Update functions
    fun onEmailChange(value: String) {
        email = value
    }

    fun onPasswordChange(value: String) {
        password = value
    }

    // Validation before logging in
    fun logInValidation() = viewModelScope.launch {
        when {
            email.isBlank() -> {
                eventsChannel.send(AllEvents.ErrorCode(1, "Email must not be empty"))
            }

            password.isBlank() -> {
                eventsChannel.send(AllEvents.ErrorCode(2, "Password must not be empty"))
            }

            else -> {
                performLogIn(email, password)
            }
        }
    }

    // Actual login logic
    private fun performLogIn(
        email: String,
        password: String
    ) = viewModelScope.launch {
        try {
            val user = logInUseCase.logInWithEmailPassword(email, password)
            user?.let {
                _firebaseUser.value = it
                eventsChannel.send(AllEvents.Message("Logged in successfully"))
                eventsChannel.send(AllEvents.ShouldNavigate)
            } ?: run {
                eventsChannel.send(AllEvents.Error("User is null"))
            }
        } catch (e: Exception) {
            Log.d("LogInVM", "Login failed: ${e.localizedMessage}")
            eventsChannel.send(AllEvents.Error(e.localizedMessage ?: "Unknown error"))
        }
    }

    // UI Events
    sealed class AllEvents {
        data class Message(val message: String) : AllEvents()
        data class ErrorCode(val code: Int, val erMsg: String) : AllEvents()
        data class Error(val error: String) : AllEvents()
        object ShouldNavigate : AllEvents()
    }
}
