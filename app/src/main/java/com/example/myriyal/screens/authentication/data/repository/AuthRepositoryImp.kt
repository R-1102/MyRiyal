package com.example.myriyal.screens.authentication.data.repository

import android.util.Log
import com.example.myriyal.screens.authentication.data.dataSources.AuthDataSource
import com.example.myriyal.screens.authentication.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor (private val dataSource: AuthDataSource): AuthRepository {
    override suspend fun signUpWithEmailPassword(
        username: String,
        email: String,
        password: String
    ): FirebaseUser ? {
        return dataSource.signUpWithEmailPassword(email,password)
    }

    override suspend fun logInWithEmailPassword(
        email: String,
        password: String
    ): FirebaseUser? {
        return dataSource.logInWithEmailPassword(email,password)
    }

    override fun logOut() {
        Log.d("AuthRepoImp LOG OUT", "logOut() called")
        dataSource.logOut()
    }

    override suspend fun sendPasswordResetEmail(email: String): Result<String> {
        return dataSource.sendPasswordResetEmail(email)
    }

    override fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }
}