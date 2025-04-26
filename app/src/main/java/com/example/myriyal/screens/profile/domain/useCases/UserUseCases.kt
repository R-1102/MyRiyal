package com.example.myriyal.screens.profile.domain.useCases

import com.example.myriyal.screens.categories.domian.model.CategoryType
import com.example.myriyal.screens.categories.domian.repository.CategoryRepository
import com.example.myriyal.screens.records.domain.repository.RecordRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * Use case responsible for calculating the user's total balance
 * by summing all income records and subtracting all expense records.
 *
 * Depends on:
 * - [RecordRepository] to retrieve all records
 * - [CategoryRepository] to determine whether each record is INCOME or EXPENSE
 *
 * Called from: [ProfileViewModel]
 */
class CalculateBalanceUseCase @Inject constructor(
    private val recordRepository: RecordRepository,
    private val categoryRepository: CategoryRepository
) {
    /**
     * Calculates the total balance by:
     * - Mapping each record to its category
     * - Adding the amount if category is INCOME
     * - Subtracting the amount if category is EXPENSE
     *
     * @return The net balance as a Double
     */
    suspend operator fun invoke(): Double {
        val records = recordRepository.getAllRecords().first()
        val categories = categoryRepository.getAllCategories().first()

        return records.sumOf { record ->
            val category = categories.find { it.categoryId == record.categoryId }
            if (category?.type == CategoryType.INCOME) record.amount else -record.amount
        }
    }
}
