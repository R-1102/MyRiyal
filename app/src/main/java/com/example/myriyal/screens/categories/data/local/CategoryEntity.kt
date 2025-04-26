package com.example.myriyal.screens.categories.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.myriyal.screens.categories.domian.model.CategoryStatus
import com.example.myriyal.screens.categories.domian.model.CategoryType

// This entity represents the "category" table in the Room database.
// It stores all expense/income categories (both predefined and user-created).
// Data flows:
// - Written by: CategoryRepositoryImpl (insert, update, delete)
// - Read by: CategoryRepositoryImpl → ViewModel → CategoryScreen
// - Used by: RecordEntity (references categoryId in its foreign key)

@Entity(
    tableName = "category",
    // Creates a unique index on the "name" column to prevent duplicate category names
    indices = [Index(value = ["name"], unique = true)]
)
data class CategoryEntity(

    // Auto-generated unique ID for each category
    @PrimaryKey(autoGenerate = true) val categoryId: Int = 0,

    // Category name (e.g., "Food", "Salary")
    val name: String,

    // Hex color code string for UI representation (e.g., "#FF0000")
    val color: String,

    // Optional icon or emoji (e.g., "🍔", "💰") for UI display
    val icon: String? = null,

    // Status indicates if the category is active or soft-deleted
    val status: CategoryStatus,

    // Indicates whether this is an income or expense category
    val type: CategoryType,

    // true if the category was inserted as a predefined system category
    val isPredefined: Boolean,

    // Creation timestamp (System.currentTimeMillis())
    val createdAt: Long,

    // Last update timestamp (used in update operations)
    val updatedAt: Long,

    @ColumnInfo(defaultValue = "0")
    val isSync: Boolean = false

)
