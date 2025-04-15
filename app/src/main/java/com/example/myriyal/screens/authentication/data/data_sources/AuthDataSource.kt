package com.example.myriyal.screens.authentication.data.data_sources

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthDataSource {

    private val auth = FirebaseAuth.getInstance()

    suspend fun signUpWithEmailPassword(email: String, password: String): FirebaseUser? {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        return result.user
    }

    fun getCurrentUser(): FirebaseUser? = auth.currentUser
}