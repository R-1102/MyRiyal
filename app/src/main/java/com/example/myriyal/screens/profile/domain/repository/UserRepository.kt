package com.example.myriyal.screens.profile.domain.repository

import com.example.myriyal.screens.profile.data.local.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing user profiles.
 *
 * This abstracts the data operations related to the user table
 * and defines the contract used by the domain and presentation layers.
 *
 * Implemented by: [UserRepositoryImpl] (data layer)
 * Used in: [ProfileViewModel] → [UserUseCases] → this
 */
interface UserRepository {

    /**
     * Inserts or updates a user in the local database.
     * Called when creating a new user or updating profile info.
     */
    suspend fun insertUser(user: UserEntity)

    /**
     * Deletes a user from the database.
     * Called when removing a profile (if supported).
     */
    suspend fun deleteUser(user: UserEntity)

    /**
     * Retrieves all users as a Flow stream.
     * Typically used in single-user mode to load the first profile.
     */
    fun getAllUsers(): Flow<List<UserEntity>>
}
