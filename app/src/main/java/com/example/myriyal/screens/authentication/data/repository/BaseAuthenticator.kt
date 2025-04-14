package com.example.myriyal.screens.authentication.data.repository

import com.google.firebase.auth.FirebaseUser

interface BaseAuthenticator{

    suspend fun signUpWithEmailPassword(
        username:String,
        email:String,
        password:String
    ):FirebaseUser?


}