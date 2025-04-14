package com.example.myriyal.screens.authentication.domain.repository

import com.google.firebase.auth.FirebaseUser


/*
use this interface to implement all authentication functions
[Sign up], [Log in], [Log out], [Reset password]
all classes that implement those functionality will be inherit from this interface!
 */


interface BaseAuthRepository{

    suspend fun signUpWithEmailPassword(
        username:String,
        email: String,
        password: String) : FirebaseUser?

}