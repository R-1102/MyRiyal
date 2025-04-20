package com.example.myriyal.core.local.dao

import androidx.room.*
import com.example.myriyal.core.local.entities.RecordEntity
import kotlinx.coroutines.flow.Flow

// DAO for the "record" table.
// Provides CRUD operations for storing financial records (income or expense entries).
//
// Data flows:
// - Called by: RecordRepositoryImpl
// - Receives requests from: ViewModel (RecordViewModel) → Repository → DAO
// - Sends data to: UI (Record/Transaction history screen)

@Dao
interface RecordDao {

    /**
     * Insert a new record into the database.
     * If a record with the same primary key exists, this will fail unless you handle the conflict externally.
     * This is used for true insert operations only.
     */
    @Insert(onConflict = OnConflictStrategy.ABORT) // no longer using REPLACE, we separate insert/update
    suspend fun insertRecord(record: RecordEntity)

    /**
     * Update an existing record.
     * Matches record by primary key and updates all fields.
     * Called by: RecordRepositoryImpl.update
     */
    @Update
    suspend fun updateRecord(record: RecordEntity)

    /**
     * Delete a specific record from the database.
     * Called by: RecordRepositoryImpl.delete
     */
    @Delete
    suspend fun deleteRecord(record: RecordEntity)

    /**
     * Fetch all records, ordered by date descending.
     * Used to display all transaction history in the UI.
     */
    @Query("SELECT * FROM record ORDER BY date DESC")
    fun getAllRecords(): Flow<List<RecordEntity>>

    /**
     * Fetch all records for a specific category ID.
     * Useful for filtering or visualizing category-specific transactions.
     */
    @Query("SELECT * FROM record WHERE categoryId = :categoryId ORDER BY date DESC")
    fun getRecordsByCategory(categoryId: Int): Flow<List<RecordEntity>>

    /**
     * Fetch a single record by its ID.
     * Used when editing or viewing details of a specific transaction.
     */
    @Query("SELECT * FROM record WHERE recordId = :recordId LIMIT 1")
    suspend fun getRecordById(recordId: Int): RecordEntity?

    /**
     * Delete all records tied to a specific category ID.
     * Helps maintain referential integrity if a category is removed.
     */
    @Query("DELETE FROM record WHERE categoryId = :categoryId")
    suspend fun deleteRecordsByCategory(categoryId: Int)


    /**
     * Calculate total balance as a reactive Flow<Double>.
     * Uses two subqueries: one for total income, one for total expenses.
     */
    @Query("""
    SELECT 
        (SELECT IFNULL(SUM(amount), 0) FROM record 
         INNER JOIN category ON record.categoryId = category.categoryId 
         WHERE category.type = 'INCOME')
        -
        (SELECT IFNULL(SUM(amount), 0) FROM record 
         INNER JOIN category ON record.categoryId = category.categoryId 
         WHERE category.type = 'EXPENSE')
""")
    fun getTotalBalance(): Flow<Double>


    /**
     * Calculate total balance as a one-time suspend function.
     * Uses a conditional SUM based on the category type.
     */
    @Query("SELECT IFNULL(SUM(CASE WHEN category.type = 'INCOME' THEN record.amount ELSE -record.amount END), 0.0) " +
            "FROM record INNER JOIN category ON record.categoryId = category.categoryId")
    suspend fun calculateTotalBalance(): Double



    // Search records by name using a LIKE query. Returns results ordered by date (newest first).
    @Query("SELECT * FROM record WHERE name LIKE '%' || :query || '%' ORDER BY date DESC")
    fun searchRecordsByName(query: String): Flow<List<RecordEntity>>

}
