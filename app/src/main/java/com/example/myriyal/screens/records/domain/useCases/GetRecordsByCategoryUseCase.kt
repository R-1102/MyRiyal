package com.example.myriyal.screens.records.domain.useCases

import com.example.myriyal.core.local.entities.RecordEntity
import com.example.myriyal.screens.records.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for retrieving all records that belong to a specific category.
 *
 * Purpose:
 * - Filters records based on categoryId (e.g., all "Food" or "Transport" expenses).
 * - Used when the UI needs to show records grouped or filtered by category.
 *
 * Called from: RecordViewModel → UI
 * Delegates to: RecordRepository → RecordRepositoryImpl → RecordDao
 */
class GetRecordsByCategoryUseCase(
    private val repository: RecordRepository
) {
    /**
     * Executes the use case.
     *
     * @param categoryId ID of the category whose records should be retrieved.
     * @return A Flow that emits the list of matching RecordEntity objects.
     */
    operator fun invoke(categoryId: Int): Flow<List<RecordEntity>> {
        return repository.getRecordsByCategory(categoryId)
    }
}
