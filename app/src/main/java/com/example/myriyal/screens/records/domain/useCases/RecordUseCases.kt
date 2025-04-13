package com.example.myriyal.screens.records.domain.useCases

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
 */
data class RecordUseCases(
    val insert: InsertRecordUseCase,
    val update: UpdateRecordUseCase,
    val delete: DeleteRecordUseCase,
    val getAllRecords: GetAllRecordsUseCase,
    val getByCategory: GetRecordsByCategoryUseCase,
    val getRecordById: GetRecordByIdUseCase
)
