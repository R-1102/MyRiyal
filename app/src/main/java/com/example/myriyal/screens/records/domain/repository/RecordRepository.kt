package com.example.myriyal.screens.records.domain.repository

import com.example.myriyal.core.local.entities.RecordEntity
import kotlinx.coroutines.flow.Flow

/**
 * RecordRepository defines all use cases related to the "record" feature.
 * It serves as the contract for the domain layer, abstracting data access.
 *
 * Implemented by: [RecordRepositoryImpl] in the data layer.
 * Called by: Use Cases in the domain layer → ViewModels in the presentation layer.
 */
interface RecordRepository {

    /**
     * Inserts a new record into the database.
     * Used when creating a new transaction (expense or income).
     *
     * Called from: [InsertRecordUseCase] → [RecordViewModel] → UI
     */
    suspend fun insert(record: RecordEntity)

    /**
     * Updates an existing record.
     * Used when editing a transaction from the UI.
     *
     * Called from: [UpdateRecordUseCase] → [RecordViewModel] → UI
     */
    suspend fun update(record: RecordEntity)

    /**
     * Deletes a specific record from the database.
     * Used when the user removes a record from their history.
     *
     * Called from: [DeleteRecordUseCase] → [RecordViewModel] → UI
     */
    suspend fun delete(record: RecordEntity)

    /**
     * Retrieves a specific record by its ID.
     * Useful for editing workflows or displaying record details.
     *
     * Called from: [GetRecordByIdUseCase] → [RecordViewModel]
     */
    suspend fun getRecordById(recordId: Int): RecordEntity?

    /**
     * Returns a reactive list of all records, ordered by latest date first.
     * This is the main data source for the Records screen in the UI.
     *
     * Called from: [GetAllRecordsUseCase] → [RecordViewModel]
     */
    fun getAllRecords(): Flow<List<RecordEntity>>

    /**
     * Returns a reactive list of records filtered by category.
     * Useful when displaying records inside a specific category or tracker.
     *
     * Called from: [GetRecordsByCategoryUseCase] → [RecordViewModel]
     */
    fun getRecordsByCategory(categoryId: Int): Flow<List<RecordEntity>>

    /**
     * Calculates the total balance by subtracting expenses from income.
     * This reactive Flow emits the updated total when any record changes.
     *
     * Called from: [GetTotalBalanceUseCase] → [ProfileViewModel] → UI
     */
    fun getTotalBalance(): Flow<Double>

}
