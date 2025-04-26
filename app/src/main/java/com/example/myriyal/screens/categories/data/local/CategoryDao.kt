package com.example.myriyal.screens.categories.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myriyal.screens.categories.domian.model.CategoryStatus
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

    @Query("SELECT * FROM category WHERE serverId = :serverId LIMIT 1")
    suspend fun getCategoryByServerId(serverId: String): CategoryEntity?
    // Inserts a new category into the database or replaces it if it already exists (based on primary key).
    // Called by: CategoryRepositoryImpl.insertCategory
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity): Long

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

    /**
     * Search category by name using a LIKE query. Returns results ordered by date (newest first).
     */
    @Query("SELECT * FROM category WHERE name LIKE '%' || :query || '%' ORDER BY createdAt DESC")
    fun searchCategoryByName(query:String):Flow<List<CategoryEntity>>

}
