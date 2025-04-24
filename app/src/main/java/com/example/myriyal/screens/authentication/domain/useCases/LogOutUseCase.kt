package com.example.myriyal.screens.authentication.domain.useCases

import com.example.myriyal.screens.authentication.domain.repository.AuthRepo
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val authRepo: AuthRepo){

    fun logOut(){
        authRepo.logOut()
     //   FirebaseAuth.getInstance().signOut()
    }

}