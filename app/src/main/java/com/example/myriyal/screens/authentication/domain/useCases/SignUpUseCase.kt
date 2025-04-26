package com.example.myriyal.screens.authentication.domain.useCases

import com.example.myriyal.screens.authentication.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository
){
    suspend fun signUpWithEmailPassword(
        username: String,
        email: String,
        password: String
    ): FirebaseUser? {
        return repository.signUpWithEmailPassword(username,email,password)
    }
}