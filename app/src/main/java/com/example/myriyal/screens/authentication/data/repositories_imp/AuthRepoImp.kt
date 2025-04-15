package com.example.myriyal.screens.authentication.data.repositories_imp

import com.example.myriyal.screens.authentication.data.data_sources.AuthDataSource
import com.example.myriyal.screens.authentication.domain.repository.AuthRepo
import com.google.firebase.auth.FirebaseUser

class AuthRepoImp (private val dataSource: AuthDataSource): AuthRepo {
    override suspend fun signUpWithEmailPassword(
        username: String,
        email: String,
        password: String
    ): FirebaseUser ? {
        return dataSource.signUpWithEmailPassword(email,password)
    }
}