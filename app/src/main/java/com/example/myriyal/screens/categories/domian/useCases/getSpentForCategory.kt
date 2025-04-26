package com.example.myriyal.screens.categories.domian.useCases

import com.example.myriyal.screens.records.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case for retrieving the total amount spent for a specific category.
 *
 * Data flow:
 * - Fetches data from: RecordRepository → RecordDao (Room)
 * - Sends data to: CategoryViewModel → CategoryItemCard (or any screen that displays category spending)
 */

class GetSpentAmountForCategoryUseCase @Inject constructor(
    private val repository: RecordRepository
) {
    /**
     * Returns a Flow emitting the total spent amount for a given category ID.
     *
     * @param categoryId The ID of the category to fetch spending for.
     * @return A reactive stream (Flow) emitting the total amount spent.
     */
    operator fun invoke(categoryId: Int): Flow<Double> {
        return repository.getTotalSpentForCategory(categoryId)
    }
}
