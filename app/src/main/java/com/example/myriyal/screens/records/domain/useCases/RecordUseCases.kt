package com.example.myriyal.screens.records.domain.useCases

import com.example.myriyal.screens.categories.domian.useCases.GetSpentAmountForCategoryUseCase

/**
 * Container class that groups all use cases related to the "record" feature.
 *
 * Purpose:
 * - Centralizes access to business logic for financial records (expenses/income).
 * - Makes it easier to inject all use cases into the ViewModel from one place.
 *
 * Used in:
 * - RecordViewModel (presentation layer)
 *
 * Contains:
 * - insert: Inserts a new record.
 * - update: Updates an existing record.
 * - delete: Deletes a record.
 * - getAllRecords: Retrieves all records for display.
 * - getByCategory: Retrieves records filtered by category.
 * - getRecordById: Gets a single record (e.g., for editing).
 * - getTotalBalance: Calculates the overall balance (income - expenses).
 * - searchRecordsByName: Searches records by name (for search bar functionality).
 * - getSpentAmount: Calculates total amount spent for a given category (used in budget/analytics).
 */
data class RecordUseCases(
    val insert: InsertRecordUseCase,
    val update: UpdateRecordUseCase,
    val delete: DeleteRecordUseCase,
    val getAllRecords: GetAllRecordsUseCase,
    val getByCategory: GetRecordsByCategoryUseCase,
    val getRecordById: GetRecordByIdUseCase,
    val getTotalBalance: GetTotalBalanceUseCase,
    val searchRecordsByName: SearchRecordsByNameUseCase,
    val getSpentAmount: GetSpentAmountForCategoryUseCase
)
