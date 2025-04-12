package com.example.myriyal.screens.records.domain.useCases

import com.example.myriyal.core.local.entities.RecordEntity
import com.example.myriyal.screens.records.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

class GetAllRecordsUseCase(
    private val repository: RecordRepository
) {
    operator fun invoke(): Flow<List<RecordEntity>> {
        return repository.getAllRecords()
    }
}