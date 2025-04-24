package com.example.myriyal.screens.authentication.domain.repository

interface NotificationRepository {
    suspend fun getFcmToken(): String?
}
