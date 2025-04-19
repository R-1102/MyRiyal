package com.example.myriyal.core.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

// This entity represents the "userProfile" table in the Room database.
// It stores data related to the user profile such as name and current balance.
//
// Data flows:
// - Written by: UserRepositoryImpl (insert/update)
// - Read by: UserRepositoryImpl → ViewModel (e.g., ProfileViewModel) → Screen (e.g., ProfileScreen)
// - Used to show the user's info or profile stats

@Entity(tableName = "userProfile")
data class UserEntity(

    // Unique ID for the user (e.g., "guest" or Firebase UID)
    // Not auto-generated — manually assigned by the app
    @PrimaryKey(autoGenerate = false)
    val userId: String,

    // The display name of the user (e.g., "Mohammed", "Guest")
    val userName: String,

    // The user’s current balance displayed on the profile/dashboard
    // Can be manually edited or auto-calculated from records
    val balance: Double = 0.0,

    // Timestamp for when the user profile was created
    val createdAt: Long,

    // Timestamp for when the profile was last updated
    val updatedAt: Long
)
