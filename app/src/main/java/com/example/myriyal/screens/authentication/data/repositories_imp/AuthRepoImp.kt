package com.example.myriyal.screens.authentication.data.repositories_imp

import com.example.myriyal.screens.authentication.domain.repository.AuthRepo
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class AuthRepoImp : AuthRepo {
    override suspend fun signUpWithEmailPassword(
        username: String,
        email: String,
        password: String
    ): FirebaseUser ? {
        val authResult = Firebase.auth.createUserWithEmailAndPassword(email, password).await()
        return authResult.user
    }
}