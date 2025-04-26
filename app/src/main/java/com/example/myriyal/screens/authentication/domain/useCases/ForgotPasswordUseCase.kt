package com.example.myriyal.screens.authentication.domain.useCases

import com.example.myriyal.screens.authentication.domain.repository.AuthRepository
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val authRepo: AuthRepository
) {
    suspend fun resetPassword(email:String):Result<String>{
        return authRepo.sendPasswordResetEmail(email)
    }
}