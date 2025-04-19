package com.example.myriyal.screens.authentication.domain.repository


import com.google.firebase.auth.FirebaseUser

interface AuthRepo {
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

}