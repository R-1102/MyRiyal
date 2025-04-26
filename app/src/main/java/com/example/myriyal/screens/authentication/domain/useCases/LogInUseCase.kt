package com.example.myriyal.screens.authentication.domain.useCases

import com.example.myriyal.screens.authentication.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class LogInUseCase @Inject constructor(
    private val repository: AuthRepository
){
    suspend fun logInWithEmailPassword(
        email:String,
        password:String
    ): FirebaseUser?{
        return repository.logInWithEmailPassword(email,password)
    }
}