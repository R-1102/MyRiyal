package com.example.myriyal.screens.records.domain.useCases

import com.example.myriyal.screens.records.data.local.RecordEntity
import com.example.myriyal.screens.records.domain.repository.RecordRepository

/**
 * Use case for retrieving a specific record by its ID.
 *
 * Purpose:
 * - Provides access to a single RecordEntity using its unique identifier.
 * - Typically used when editing or showing detailed information for a record.
 *
 * Called from: RecordViewModel → UI (e.g., editing screen)
 * Delegates to: RecordRepository → RecordRepositoryImpl → RecordDao
 */
class GetRecordByIdUseCase(
    private val repository: RecordRepository
) {
    /**
     * Retrieves a record by its ID or returns null if not found.
     */
    suspend operator fun invoke(recordId: Int): RecordEntity? {
        return repository.getRecordById(recordId)
    }
}
