package com.example.myriyal.core.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myriyal.core.local.entities.CategoryEntity
import com.example.myriyal.core.local.enums.CategoryStatus
import kotlinx.coroutines.flow.Flow

// DAO (Data Access Object) for the "category" table.
// Provides all database operations (insert, update, delete, query) for CategoryEntity.
//
// Data flows:
// - Called by: CategoryRepositoryImpl (data layer)
// - Receives requests from: CategoryViewModel → Repository → DAO
// - Returns data to: ViewModel → UI (CategoryScreen)


@Dao
interface CategoryDao {

    // Inserts a new category into the database or replaces it if it already exists (based on primary key).
    // Called by: CategoryRepositoryImpl.insertCategory
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity)

    // Updates an existing category in the database.
    // Called by: CategoryRepositoryImpl.updateCategory
    @Update
    suspend fun updateCategory(category: CategoryEntity)

    // Returns a stream of all categories ordered by name.
    // This is used in the UI to observe category updates in real-time.
    // Returned to: CategoryViewModel → UI (CategoryScreen)
    @Query("SELECT * FROM category ORDER BY categoryId ASC")
    fun getAllCategories(): Flow<List<CategoryEntity>>


    // Soft deletes a category by updating its status to INACTIVE.
    // Used when user chooses "Deactivate" in the UI.
    // Called by: CategoryRepositoryImpl.softDeleteCategory
    @Query("UPDATE category SET status = :status WHERE categoryId = :id")
    suspend fun updateCategoryStatus(id: Int, status: CategoryStatus)

    // Permanently deletes a category from the database.
    // Used only if user chooses "Delete Forever" from the UI.
    // Called by: CategoryRepositoryImpl.deleteCategory
    @Delete
    suspend fun deleteCategory(category: CategoryEntity)


    // Returns all categories once (non-reactive).
    // Used by: Repository during app startup to check if predefined categories exist
    @Query("SELECT * FROM category")
    suspend fun getAllCategoriesOnce(): List<CategoryEntity>


}
