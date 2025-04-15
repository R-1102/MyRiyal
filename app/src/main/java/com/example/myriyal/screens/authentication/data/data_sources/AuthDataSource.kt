package com.example.myriyal.screens.authentication.data.data_sources

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthDataSource @Inject constructor (private val auth: FirebaseAuth) {

    suspend fun signUpWithEmailPassword(email: String, password: String): FirebaseUser? {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        return result.user
    }

    fun getCurrentUser(): FirebaseUser? = auth.currentUser
}