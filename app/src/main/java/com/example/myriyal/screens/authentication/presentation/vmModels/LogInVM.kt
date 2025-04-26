package com.example.myriyal.screens.authentication.presentation.vmModels

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myriyal.screens.authentication.domain.useCases.LogInUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInVM @Inject constructor(
    private val logInUseCase: LogInUseCase
) : ViewModel() {

    // State holders for user input
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    private val _firebaseUser = MutableStateFlow<FirebaseUser?>(null)
    val firebaseUser: StateFlow<FirebaseUser?> = _firebaseUser

    // Message state to be shown in the UI
    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message

    // Flag to trigger navigation
    private val _shouldNavigate = MutableStateFlow(false)
    val shouldNavigate: StateFlow<Boolean> = _shouldNavigate


    // Input change functions, setting user input from UI in here to do the business logic
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
                showMessage("Email must not be empty")
            }

            password.isBlank() -> {
                showMessage("Password must not be empty")
            }

            else -> {
                performLogIn(email, password)
            }
        }
    }


    // Show sign-up status to UI (success or error)
    private fun showMessage(msg: String) {
        _message.value = msg
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
                _message.value = "Logged in successfully!"
                _shouldNavigate.value = true
            } ?: run {
                showMessage("No account with this email, Sign Up First")
            }
        } catch (e: Exception) {
            Log.d("LogInVM", "Login failed: ${e.localizedMessage}")
            showMessage(e.localizedMessage ?: "Unknown error")
        }
    }

    // Reset navigation flag after successful navigation
    fun resetNavigation() {
        _shouldNavigate.value = false
    }

    // Clear message state after it is shown
    fun clearMessage() {
        _message.value = null
    }
}
