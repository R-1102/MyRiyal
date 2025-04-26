package com.example.myriyal.screens.records.data.repository

import com.example.myriyal.screens.records.data.local.RecordDao
import com.example.myriyal.screens.records.data.local.RecordEntity
import com.example.myriyal.screens.records.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Repository implementation for the Record feature.
 *
 * Acts as the data layer that talks to the Record DAO.
 * Injected via Hilt and used by domain-layer use cases.
 */
class RecordRepositoryImpl @Inject constructor(
    private val dao: RecordDao //  DAO is injected via Hilt
) : RecordRepository {

    /**
     * Inserts a new record into the database.
     */
    override suspend fun insert(record: RecordEntity) {
        dao.insertRecord(record)
    }

    /**
     * Updates an existing record in the database.
     */
    override suspend fun update(record: RecordEntity) {
        dao.updateRecord(record)
    }

    /**
     * Deletes a specific record.
     */
    override suspend fun delete(record: RecordEntity) {
        dao.deleteRecord(record)
    }

    /**
     * Retrieves a single record by its ID.
     */
    override suspend fun getRecordById(recordId: Int): RecordEntity? {
        return dao.getRecordById(recordId)
    }

    /**
     * Gets all records in descending order of date.
     */
    override fun getAllRecords(): Flow<List<RecordEntity>> {
        return dao.getAllRecords()
    }

    /**
     * Retrieves records filtered by category ID.
     */
    override fun getRecordsByCategory(categoryId: Int): Flow<List<RecordEntity>> {
        return dao.getRecordsByCategory(categoryId)
    }

    /**
     * Returns the user's total balance (income - expenses).
     */
    override fun getTotalBalance(): Flow<Double> {
        return dao.getTotalBalance()
    }

    /**
     * Searches records by name using a LIKE query.
     * Used by: Search bar in ViewRecordScreen.
     */
    override fun searchRecordsByName(query: String): Flow<List<RecordEntity>> {
        return dao.searchRecordsByName(query)
    }
    /**
     * Delegates the request to the DAO to calculate the total spent amount for a specific category.
     *
     * Data flow:
     * - Fetches from: RecordDao.getTotalSpentForCategory
     * - Sends to: Domain layer (use cases like GetSpentAmountForCategoryUseCase)
     */
    override fun getTotalSpentForCategory(categoryId: Int): Flow<Double> {
        return dao.getTotalSpentForCategory(categoryId)
    }
}
