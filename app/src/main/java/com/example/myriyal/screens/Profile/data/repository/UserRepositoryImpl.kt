package com.example.myriyal.screens.Profile.data.repository

import com.example.myriyal.core.local.dao.UserDao
import com.example.myriyal.core.local.entities.UserEntity
import com.example.myriyal.screens.Profile.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of [UserRepository] that interacts with the local Room database via [UserDao].
 *
 * Handles CRUD operations for user profiles stored in the "userProfile" table.
 *
 * Called by: Use cases in the domain layer (e.g., CreateGuestUserUseCase)
 * Accessed from: ViewModel (e.g., ProfileViewModel)
 */
class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    /**
     * Inserts or updates a user profile in the database.
     * Used when creating a guest user or updating profile info.
     */
    override suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    /**
     * Deletes a user profile from the database.
     * Can be used to remove saved user data.
     */
    override suspend fun deleteUser(user: UserEntity) {
        userDao.deleteUser(user)
    }

    /**
     * Returns a Flow stream of all user profiles.
     * Typically used in single-user mode to fetch the active user.
     */
    override fun getAllUsers(): Flow<List<UserEntity>> {
        return userDao.getAllUsers()
    }
}
