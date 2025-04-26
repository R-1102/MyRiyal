package com.example.myriyal.screens.records.domain.useCases

import com.example.myriyal.screens.records.data.local.RecordEntity
import com.example.myriyal.screens.records.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case to search records by their name.
 *
 * Called from: RecordViewModel → UI (e.g., search bar in Record screen)
 */
class SearchRecordsByNameUseCase(
    private val repository: RecordRepository
) {
    /**
     * Executes the search query.
     * @param query The search string to filter record names.
     * @return A flow of records matching the query.
     */
    operator fun invoke(query: String): Flow<List<RecordEntity>> {
        return repository.searchRecordsByName(query)
    }
}
