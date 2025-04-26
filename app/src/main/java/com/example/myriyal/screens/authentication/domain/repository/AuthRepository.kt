package com.example.myriyal.screens.authentication.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun signUpWithEmailPassword(
        username: String,
        email: String,
        password: String
    ): FirebaseUser?

    suspend fun logInWithEmailPassword(
        email: String,
        password: String
    ):FirebaseUser?

    fun logOut()

    suspend fun sendPasswordResetEmail(
        email: String
    ) : Result<String>

    fun getCurrentUser(): FirebaseUser?
}