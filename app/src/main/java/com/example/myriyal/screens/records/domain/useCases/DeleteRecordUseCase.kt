package com.example.myriyal.screens.records.domain.useCases

import com.example.myriyal.core.local.entities.RecordEntity
import com.example.myriyal.screens.records.domain.repository.RecordRepository

class DeleteRecordUseCase(
    private val repository: RecordRepository
) {
    suspend operator fun invoke(record: RecordEntity) {
        repository.delete(record)
    }
}