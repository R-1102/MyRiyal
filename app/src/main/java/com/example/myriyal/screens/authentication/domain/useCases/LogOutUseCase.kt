package com.example.myriyal.screens.authentication.domain.useCases

import com.example.myriyal.screens.authentication.domain.repository.AuthRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val authRepo: AuthRepository){

    fun logOut(){
        authRepo.logOut()
    }
}