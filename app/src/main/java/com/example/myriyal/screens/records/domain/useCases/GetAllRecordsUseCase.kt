package com.example.myriyal.screens.records.domain.useCases

import com.example.myriyal.screens.records.data.local.RecordEntity
import com.example.myriyal.screens.records.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for retrieving all financial records.
 *
 * Purpose:
 * - Encapsulates the logic for fetching all records from the database.
 * - Keeps the domain logic separate from the UI and data layers.
 *
 * Called from: RecordViewModel → UI (e.g., RecordScreen)
 * Delegates to: RecordRepository → RecordRepositoryImpl → RecordDao
 */
class GetAllRecordsUseCase(
    private val repository: RecordRepository
) {
    /**
     * Returns a Flow list of all records in the database.
     */
    operator fun invoke(): Flow<List<RecordEntity>> {
        return repository.getAllRecords()
    }
}
