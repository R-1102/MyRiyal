package com.example.myriyal.screens.authentication.data.repository

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class FirebaseAuthenticator @Inject constructor(): BaseAuthenticator{
    override suspend fun signUpWithEmailPassword(
        username: String,
        email: String,
        password: String
    ): FirebaseUser? {
        return Firebase.auth.currentUser
    }
}