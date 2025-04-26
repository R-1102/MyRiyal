package com.example.myriyal.screens.authentication.data.repository

import com.example.myriyal.screens.authentication.domain.repository.NotificationRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : NotificationRepository {

    override suspend fun getFcmToken(): String? {
        return firebaseAuth.currentUser?.getIdToken(true)?.await()?.token
    }
}
