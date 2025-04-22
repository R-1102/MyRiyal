package com.example.myriyal.screens.categories.domian.useCases

import com.example.myriyal.core.local.entities.TrackerEntity
import com.example.myriyal.screens.categories.domian.repository.TrackerRepository
import kotlinx.coroutines.flow.firstOrNull

/**
 * Use case for retrieving tracker information associated with a specific category.
 *
 * Data flow:
 * - Fetches data from: TrackerRepository → TrackerDao (Room database)
 * - Sends data to: CategoryViewModel → CategoryForm (for displaying or editing tracker)
 *
 * This use case emits only the first available tracker entry for the given category ID, or null if none exists.
 */
class GetTrackerByCategoryIdUseCase(
    private val repository: TrackerRepository
) {
    /**
     * Retrieves the tracker assigned to a specific category, if available.
     *
     * @param categoryId The ID of the category whose tracker is being requested.
     * @return The associated TrackerEntity or null if no tracker exists.
     */
    suspend operator fun invoke(categoryId: Int): TrackerEntity? {
        return repository.getTrackerByCategoryId(categoryId).firstOrNull()
    }
}
