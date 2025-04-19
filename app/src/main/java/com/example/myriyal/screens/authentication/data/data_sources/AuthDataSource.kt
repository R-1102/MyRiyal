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

    suspend fun logInWithEmailPassword(
        email: String,
        password: String
    ):FirebaseUser?{
        val result = auth.signInWithEmailAndPassword(email,password).await()
        return result.user
    }

    suspend fun sendPasswordResetEmail(email: String): Result<String> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Result.success("Password reset email sent successfully.")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}