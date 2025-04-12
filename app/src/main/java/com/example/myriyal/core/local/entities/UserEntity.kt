package com.example.myriyal.core.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// This entity represents the "userProfile" table in the Room database.
// It stores data related to the user profile such as name and current balance.
//
// Data flows:
// - Written by: UserRepositoryImpl (insert, update, delete)
// - Read by: UserRepositoryImpl → ViewModel (if implemented) → Screen (e.g., ProfileScreen)
// - This data is used to show the user's info or profile stats

@Entity(tableName = "userProfile")
data class UserEntity(

    // Auto-generated unique ID for each user (supports multi-user if needed)
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,

    // The display name of the user (e.g., "Mohammed")
    val userName: String,

    // The user's current balance shown in the dashboard or profile
    val balance: Double,

    // Timestamp for when the user profile was created
    val createdAt: Long,

    // Timestamp for when the profile was last updated
    val updatedAt: Long
)
