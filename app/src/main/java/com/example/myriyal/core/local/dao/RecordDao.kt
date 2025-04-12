package com.example.myriyal.core.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myriyal.core.local.entities.RecordEntity
import kotlinx.coroutines.flow.Flow

// DAO for the "record" table.
// Provides CRUD operations for storing financial records (income or expense entries).
//
// Data flows:
// - Called by: RecordRepositoryImpl
// - Receives requests from: ViewModel (e.g., RecordViewModel) → Repository → DAO
// - Sends data to: UI (Record/Transaction history screen)

@Dao
interface RecordDao {

    // Inserts a new record into the database or replaces an existing one with the same primary key.
    // Called by: RecordRepositoryImpl.insertRecord
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: RecordEntity)

    // Retrieves all records in descending order of date (latest first).
    // Used to display a full list of transactions in the UI.
    // Returned to: RecordViewModel → UI screen
    @Query("SELECT * FROM record ORDER BY date DESC")
    fun getAllRecords(): Flow<List<RecordEntity>>

    // Retrieves all records linked to a specific category (e.g., all "Food" expenses).
    // Used when filtering transactions by category in the UI.
    @Query("SELECT * FROM record WHERE categoryId = :categoryId ORDER BY date DESC")
    fun getRecordsByCategory(categoryId: Int): Flow<List<RecordEntity>>

    // Deletes a specific record from the database.
    // Called by: RecordRepositoryImpl.deleteRecord
    @Delete
    suspend fun deleteRecord(record: RecordEntity)

    // Retrieves a single record by its ID.
    // Used when needing to edit a specific record or display full details.
    @Query("SELECT * FROM record WHERE recordId = :recordId LIMIT 1")
    suspend fun getRecordById(recordId: Int): RecordEntity?

    // Deletes all records linked to a specific category.
    // Useful when a category is deleted and its related transactions must be removed.
    @Query("DELETE FROM record WHERE categoryId = :categoryId")
    suspend fun deleteRecordsByCategory(categoryId: Int)
}
