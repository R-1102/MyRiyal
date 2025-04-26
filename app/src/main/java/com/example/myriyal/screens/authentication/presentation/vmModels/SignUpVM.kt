package com.example.myriyal.screens.authentication.presentation.vmModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import android.util.Log
import androidx.compose.runtime.*
import com.example.myriyal.screens.authentication.domain.useCases.SignUpUseCase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@HiltViewModel
class SignUpVM @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel(){

    private val _firebaseUser = MutableStateFlow<FirebaseUser?>(null)
    val firebaseUser: StateFlow<FirebaseUser?> = _firebaseUser

    // User input
    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    // Message state to be shown in the UI
    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message

    // Flag to trigger navigation
    private val _shouldNavigate = MutableStateFlow(false)
    val shouldNavigate: StateFlow<Boolean> = _shouldNavigate

    // Input change functions, setting user input from UI in here to do the business logic
    fun onUsernameChange(value: String) {
        username = value
    }

    fun onEmailChange(value:String){
        email = value
    }

    fun onPasswordChange(value:String){
        password = value
    }

    fun onConfirmPassword(value:String){
        confirmPassword = value
    }

    // Validate input before sign-up
    fun signUpValidation(username: String, email: String, password: String, confirmPassword: String) = viewModelScope.launch {
        when {
            username.isBlank() ->
                showMessage("Username must not be empty")

            email.isBlank() ->
                showMessage("Email must not be empty")

            password.isBlank() ->
                showMessage("Password must not be empty")

            password != confirmPassword ->
                showMessage("Passwords do not match")

            else ->
                actualSignUpUser(username, email, password)  // If validation success, proceed with actual sign-up
        }
    }

    // Show sign-up status to UI (success or error)
    private fun showMessage(msg: String) {
        _message.value = msg
    }

    // Create users
    private fun actualSignUpUser(
        username: String,
        email: String,
        password:String
    )= viewModelScope.launch{
        try{
            val user = signUpUseCase.signUpWithEmailPassword(username,email,password)
            user?.let {
                val uid = it.uid

                val remotedb = Firebase.firestore
                val userMap = mapOf(
                    "username" to username,
                    "email" to email,
                )

                remotedb.collection("users").document(uid).set(userMap).await()

                Log.d("SignUpVM", "actualSignUpUser() called")

                _firebaseUser.value = user
                _message.value = "Signed up successfully"
                _shouldNavigate.value = true  // trigger navigation only when signUp successfully
            }

        } catch(e:Exception){
            Log.d("SignupVM","SignUp user: ${e.localizedMessage}")
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

