package com.example.myriyal.screens.authentication.presentation.vmModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myriyal.screens.authentication.domain.repository.BaseAuthRepository
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@HiltViewModel
class SignUpVM @Inject constructor(
    private val repository: BaseAuthRepository,
    var username: String,
    var email: String,
    var password: String,
    var confirmPassword: String
) : ViewModel(){

    private val _firebaseUser = MutableLiveData<FirebaseUser?>()
    val firebaseUser: LiveData<FirebaseUser?> = _firebaseUser

    // User input
    //var username by mutableStateOf("")
//    var email by mutableStateOf("")
//    var password by mutableStateOf("")
//    var confirmPassword by mutableStateOf("")



    //chanel for all the UI events
    private val eventsChannel = Channel<AllEvents>()
    val allEventsFlow = eventsChannel.receiveAsFlow()


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
    fun signUpValidation(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    )= viewModelScope.launch{

        when {
            username.isBlank() -> {
                eventsChannel.send(AllEvents.ErrorCode(1, "Username must not be empty"))
            }

            email.isBlank() -> {
                eventsChannel.send(AllEvents.ErrorCode(2, "Email must not be empty"))
            }

            password.isBlank() -> {
                eventsChannel.send(AllEvents.ErrorCode(4, "Password must not be empty"))
            }

            password != confirmPassword -> {
                eventsChannel.send(AllEvents.ErrorCode(5, "Passwords do not match"))
            }

            else -> {
                actualSignUpUser(username, email, password) //if passed validation, send to actual sign-up
            }
        }
    }



    // Create users
    private fun actualSignUpUser(
        username: String,
        email: String,
        password:String
    )= viewModelScope.launch{
        try{
            val user = repository.signUpWithEmailPassword(username,email,password)
            user?.let {
                val uid = it.uid

                val remotedb = Firebase.firestore
                val userMap = mapOf(
                    "username" to username,
                    "email" to email,
                )

                remotedb.collection("users").document(uid).set(userMap).await()

                _firebaseUser.postValue(it)

                eventsChannel.send(AllEvents.Message("Signed up successfully"))
            }

        } catch(e:Exception){
            Log.d("SignupVM","SignUp user: ${e.localizedMessage}")
            eventsChannel.send(AllEvents.Error(e.localizedMessage?:"Unknown error"))

        }



    }



    // UI events
    sealed class AllEvents {
        data class Message(val message: String) : AllEvents()
        data class ErrorCode(val code: Int, val erMsg: String) : AllEvents()
        data class Error(val error: String) : AllEvents()
    }

}
