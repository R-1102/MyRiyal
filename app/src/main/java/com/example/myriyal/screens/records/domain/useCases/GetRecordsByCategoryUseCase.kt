package com.example.myriyal.screens.records.domain.useCases

import com.example.myriyal.core.local.entities.RecordEntity
import com.example.myriyal.screens.records.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

class GetRecordsByCategoryUseCase(
    private val repository: RecordRepository
) {
    operator fun invoke(categoryId: Int): Flow<List<RecordEntity>> {
        return repository.getRecordsByCategory(categoryId)
    }
}