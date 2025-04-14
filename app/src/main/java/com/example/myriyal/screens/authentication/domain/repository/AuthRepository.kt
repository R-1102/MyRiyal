package com.example.myriyal.screens.authentication.domain.repository

import com.example.myriyal.screens.authentication.data.repository.BaseAuthenticator
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authenticator: BaseAuthenticator
): BaseAuthRepository{
    override suspend fun signUpWithEmailPassword(
        username: String,
        email: String,
        password: String
    ): FirebaseUser? {
        return authenticator.signUpWithEmailPassword(username, email , password)
    }
}