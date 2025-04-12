package com.example.myriyal.core.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// This entity represents the "trackers" table in the Room database.
// It is used to store budget tracking goals linked to specific categories.
//
// Data flows:
// - Written by: TrackerRepositoryImpl (insert, delete)
// - Read by: TrackerRepositoryImpl → ViewModel → Screen (e.g., BudgetTrackerScreen)
// - CategoryEntity is used as a foreign key reference to define the scope of the budget tracker.

@Entity(
    tableName = "trackers",
    // Foreign key ensures that each tracker is linked to a valid category.
    // If a category is deleted, its related tracker will also be deleted.
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["categoryId"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    // Index improves performance when filtering trackers by category
    indices = [Index("categoryId")]
)
data class TrackerEntity(

    // Unique auto-increment ID for each budget tracker
    @PrimaryKey(autoGenerate = true) val trackerId: Int = 0,

    // Foreign key: links this tracker to a category (e.g., "Food")
    val categoryId: Int,

    // Total budget amount to track against this category
    val budgetAmount: Double,

    // Start date of the budget tracking period (e.g., beginning of month)
    val startDate: Long,

    // Timestamp when the tracker was created
    val createdAt: Long,

    // Timestamp when the tracker was last updated
    val updatedAt: Long
)
