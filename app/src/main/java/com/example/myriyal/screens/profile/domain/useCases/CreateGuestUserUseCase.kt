package com.example.myriyal.screens.profile.domain.useCases

import com.example.myriyal.core.local.entities.UserEntity
import com.example.myriyal.screens.profile.domain.repository.UserRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * Use case for creating a default "guest" user profile in the local Room database.
 *
 * This is used to ensure that there's always at least one user in the system
 * (useful for offline mode or before signing in with Firebase).
 *
 * Injected by Hilt via constructor injection.
 */
class CreateGuestUserUseCase @Inject constructor(
    private val userRepository: UserRepository // Repository that handles user data operations
) {
    /**
     * Executes the use case:
     * - Checks if there are any existing users in the database.
     * - If none exist, inserts a new guest user with default values.
     */
    suspend operator fun invoke() {
        val existingUsers = userRepository.getAllUsers().first() // Collect the first snapshot of user list
        if (existingUsers.isEmpty()) {
            val guestUser = UserEntity(
                userId = "guest", // A static ID to differentiate guest users
                userName = "Guest", // Default name
                balance = 0.0, // Starting balance
                createdAt = System.currentTimeMillis(), // Current timestamp
                updatedAt = System.currentTimeMillis()  // Same as created initially
            )
            userRepository.insertUser(guestUser) // Save guest user to the database
        }
    }
}
