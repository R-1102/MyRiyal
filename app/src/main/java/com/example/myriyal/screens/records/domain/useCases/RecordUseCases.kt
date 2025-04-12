package com.example.myriyal.screens.records.domain.useCases

data class RecordUseCases(
    val insert: InsertRecordUseCase,
    val update: UpdateRecordUseCase,
    val delete: DeleteRecordUseCase,
    val getAllRecords: GetAllRecordsUseCase,
    val getByCategory: GetRecordsByCategoryUseCase,
    val getRecordById: GetRecordByIdUseCase
)