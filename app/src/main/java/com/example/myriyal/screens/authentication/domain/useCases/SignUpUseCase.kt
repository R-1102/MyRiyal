package com.example.myriyal.screens.authentication.domain.useCases

import com.example.myriyal.screens.authentication.data.repositories_imp.AuthRepoImp
import com.google.firebase.auth.FirebaseUser

class SignUpUseCase (
    private val repository: AuthRepoImp
){

    suspend fun signUpWithEmailPassword(
        username: String,
        email: String,
        password: String
    ): FirebaseUser? {
        return repository.signUpWithEmailPassword(username,email,password)
    }
}