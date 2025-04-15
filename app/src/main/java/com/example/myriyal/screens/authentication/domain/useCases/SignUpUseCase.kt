package com.example.myriyal.screens.authentication.domain.useCases

import com.example.myriyal.screens.authentication.domain.repository.AuthRepo
import com.google.firebase.auth.FirebaseUser

class SignUpUseCase (
    private val repository: AuthRepo
){

    suspend fun signUpWithEmailPassword(
        username: String,
        email: String,
        password: String
    ): FirebaseUser? {
        return repository.signUpWithEmailPassword(username,email,password)
    }
}