package com.example.myriyal.screens.profile.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// DAO for the "userProfile" table.
// Provides operations to insert, retrieve, and delete user profile data.
//
// Data flows:
// - Called by: UserRepositoryImpl
// - Receives requests from: UserViewModel (if implemented)
// - Used to show data in profile-related screens or dashboard

@Dao
interface UserDao {

    // Inserts a new user into the database or replaces an existing user with the same primary key.
    // Called by: UserRepositoryImpl.insertUser
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    // Retrieves all user profiles ordered by creation date (most recent first).
    // Returned to: UserRepositoryImpl → ViewModel → UI
    @Query("SELECT * FROM userProfile ORDER BY createdAt DESC")
    fun getAllUsers(): Flow<List<UserEntity>>

    // Deletes a specific user from the database.
    // Called by: UserRepositoryImpl.deleteUser
    @Delete
    suspend fun deleteUser(user: UserEntity)
}
