package com.example.myriyal.screens.records.domain.useCases

import com.example.myriyal.screens.records.data.local.RecordEntity
import com.example.myriyal.screens.records.domain.repository.RecordRepository

/**
 * Use case for inserting a new financial record into the database.
 *
 * Purpose:
 * - Adds a new record such as an expense or income entry to the local Room database.
 * - Wraps the data layer logic in a domain-level use case to ensure separation of concerns.
 *
 * Called from: RecordViewModel → UI (when user submits a new record form)
 * Delegates to: RecordRepository → RecordRepositoryImpl → RecordDao
 */
class InsertRecordUseCase(
    private val repository: RecordRepository
) {
    /**
     * Executes the insert operation.
     *
     * @param record A RecordEntity representing the new transaction to be saved.
     */
    suspend operator fun invoke(record: RecordEntity) {
        repository.insert(record)
    }
}
