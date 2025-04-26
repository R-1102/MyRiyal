package com.example.myriyal.screens.authentication.data.repository

import android.util.Log
import com.example.myriyal.screens.authentication.domain.repository.NotificationRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * Implementation of the NotificationRepository interface.
 * Handles fetching the **Firebase Authentication ID Token** for the currently signed-in user.
 *
 * Data flows:
 * - Called by: NotificationViewModel (presentation layer)
 * - Depends on: FirebaseAuth (authentication service)
 * - Purpose: Fetch and provide the user's **Authentication ID Token** securely.
 */
class NotificationRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : NotificationRepository {

    /**
     * Fetches the **Firebase Authentication ID Token** for the currently signed-in user.
     *
     * Returns:
     * - The ID Token as a String if successful.
     * - Null if the user is not signed in or if an error occurs during token retrieval.
     *
     * Logs:
     * - Success and error states are logged for debugging purposes.
     */
    override suspend fun getFcmToken(): String? {
        // Note: Despite method name, this fetches the Authentication ID Token, not the FCM device token.

        val user = firebaseAuth.currentUser

        if (user == null) {
            Log.e("TokenAuth", "No Firebase user is currently signed in.")
            return null
        }

        return try {
            val tokenResult = user.getIdToken(true).await()
            val token = tokenResult.token
            if (token != null) {
                Log.d("TokenAuth", "Got Firebase Auth ID Token: $token")
            } else {
                Log.w("TokenAuth", "Auth ID Token is null after fetching.")
            }
            token
        } catch (e: Exception) {
            Log.e("TokenAuth", "Failed to fetch Firebase Auth ID Token: ${e.message}")
            null
        }
    }
}
