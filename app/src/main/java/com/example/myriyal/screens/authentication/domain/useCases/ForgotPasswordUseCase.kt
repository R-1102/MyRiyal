package com.example.myriyal.screens.authentication.domain.useCases

import com.example.myriyal.screens.authentication.domain.repository.AuthRepo
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val authRepo: AuthRepo
) {
    suspend fun resetPassword(email:String):Result<String>{
        return authRepo.sendPasswordResetEmail(email)
    }
}