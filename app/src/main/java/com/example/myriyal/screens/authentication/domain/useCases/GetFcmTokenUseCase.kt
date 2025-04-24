package com.example.myriyal.screens.authentication.domain.useCases

import com.example.myriyal.screens.authentication.domain.repository.NotificationRepository
import javax.inject.Inject

class GetFcmTokenUseCase @Inject constructor(private val repository: NotificationRepository) {
    suspend fun execute(): String? {
        return repository.getFcmToken()
    }
}
