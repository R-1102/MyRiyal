package com.example.myriyal.screens.authentication.domain.repository


import com.google.android.gms.tasks.Task

interface NotificationRepository {
    fun getFcmToken(): Task<String>
}
