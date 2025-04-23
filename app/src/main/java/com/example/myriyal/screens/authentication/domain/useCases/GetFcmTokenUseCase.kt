package com.example.myriyal.screens.authentication.domain.useCases

import com.example.myriyal.screens.authentication.domain.repository.NotificationRepository
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class GetFcmTokenUseCase @Inject constructor(private val repository: NotificationRepository) {
    fun execute(): Task<String> {
        return repository.getFcmToken()
    }
}
