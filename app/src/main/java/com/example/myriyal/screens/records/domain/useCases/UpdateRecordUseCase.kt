package com.example.myriyal.screens.records.domain.useCases

import com.example.myriyal.core.local.entities.RecordEntity
import com.example.myriyal.screens.records.domain.repository.RecordRepository

/**
 * Use case for updating an existing record in the database.
 *
 * Purpose:
 * - Called when the user edits a record and submits the changes.
 *
 * Flow:
 * - UI → RecordViewModel → UpdateRecordUseCase → RecordRepository → RecordDao
 *
 * Used in:
 * - RecordViewModel
 */
class UpdateRecordUseCase(
    private val repository: RecordRepository
) {
    suspend operator fun invoke(record: RecordEntity) {
        repository.update(record)
    }
}
