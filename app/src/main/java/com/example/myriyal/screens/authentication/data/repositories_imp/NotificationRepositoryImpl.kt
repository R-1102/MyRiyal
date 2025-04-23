package com.example.myriyal.screens.authentication.data.repositories_imp

import com.example.myriyal.screens.authentication.domain.repository.NotificationRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor() : NotificationRepository {
    override fun getFcmToken(): Task<String> {
        return FirebaseMessaging.getInstance().token
    }
}
