package com.example.myriyal.screens.records.domain.useCases

import com.example.myriyal.core.local.entities.RecordEntity
import com.example.myriyal.screens.records.domain.repository.RecordRepository

/**
 * Use case for deleting a record.
 *
 * Purpose:
 * - Encapsulates the deletion logic of a financial record (income or expense).
 * - Maintains separation of concerns by isolating domain logic from UI and data layers.
 *
 * Called from: RecordViewModel → UI (e.g., RecordScreen)
 * Delegates to: RecordRepository → RecordRepositoryImpl → RecordDao
 */
class DeleteRecordUseCase(
    private val repository: RecordRepository
) {
    /**
     * Executes the deletion of the given [record].
     */
    suspend operator fun invoke(record: RecordEntity) {
        repository.delete(record)
    }
}
