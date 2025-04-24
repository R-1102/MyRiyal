package com.example.myriyal.core.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.myriyal.screens.categories.data.local.CategoryEntity

// This entity represents the "records" table in the Room database.
// Each record stores a financial entry (expense or income) tied to a specific category.
//
// Data flows:
// - Written by: RecordRepositoryImpl (insert, update, delete)
// - Read by: RecordRepositoryImpl → ViewModel → Screen (e.g., TransactionHistoryScreen)
// - CategoryEntity is used as a foreign key reference to classify each record.

@Entity(
    tableName = "record",
    // Enforce foreign key: each record must be linked to an existing category.
    // If a category is deleted, all its related records will be deleted as well.
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["categoryId"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    // Index added on categoryId to improve query performance for category-based filtering
    indices = [Index("categoryId")]
)
data class RecordEntity(

    // Unique auto-increment ID for each financial record
    @PrimaryKey(autoGenerate = true) val recordId: Int = 0,

    // Foreign key: links to the ID of the category this record belongs to
    val categoryId: Int,

    // Title or short description of the record (e.g., "Groceries", "Salary")
    val name: String,

    // Monetary value for this record
    val amount: Double,

    // Date of the transaction (used for filtering, sorting in UI)
    val date: Long,

    // Optional longer description (e.g., "Bought vegetables and snacks")
    val description: String? = null,

    // When the record was first created
    val createdAt: Long,

    // When the record was last updated
    val updatedAt: Long,

    @ColumnInfo(defaultValue = "0")
    val isSync: Boolean = false
)
