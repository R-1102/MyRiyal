package com.example.myriyal.screens.records.data.repository

import android.content.Context
import com.example.myriyal.core.local.db.DatabaseProvider
import com.example.myriyal.core.local.entities.RecordEntity
import com.example.myriyal.screens.records.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

/**
 * Repository implementation for the Record feature.
 *
 * Acts as a bridge between the domain layer (use cases) and data source (Room DAO).
 * Follows Clean Architecture principles.
 *
 * Receives calls from: UseCases → ViewModel
 * Sends data to: Room DAO (via DatabaseProvider)
 */
class RecordRepositoryImpl(private val context: Context) : RecordRepository {

    // Access the DAO from the Room database
    private val dao = DatabaseProvider.getDatabase(context).recordDao()

    /**
     * Inserts a new record into the database.
     * Fails if a record with the same ID already exists.
     * Called from: InsertRecordUseCase → ViewModel
     */
    override suspend fun insert(record: RecordEntity) {
        dao.insertRecord(record)
    }

    /**
     * Updates an existing record in the database.
     * Requires the record ID to already exist.
     * Called from: UpdateRecordUseCase → ViewModel
     */
    override suspend fun update(record: RecordEntity) {
        dao.updateRecord(record) // ✅ Now using real update
    }

    /**
     * Deletes a specific record.
     * Called from: DeleteRecordUseCase → ViewModel
     */
    override suspend fun delete(record: RecordEntity) {
        dao.deleteRecord(record)
    }

    /**
     * Retrieves a single record by its ID.
     * Used when editing or viewing full details.
     */
    override suspend fun getRecordById(recordId: Int): RecordEntity? {
        return dao.getRecordById(recordId)
    }

    /**
     * Gets all records in descending order of date.
     * Used to populate the full transaction list.
     */
    override fun getAllRecords(): Flow<List<RecordEntity>> {
        return dao.getAllRecords()
    }

    /**
     * Retrieves records filtered by category ID.
     * Used when filtering records per category.
     */
    override fun getRecordsByCategory(categoryId: Int): Flow<List<RecordEntity>> {
        return dao.getRecordsByCategory(categoryId)
    }
}
