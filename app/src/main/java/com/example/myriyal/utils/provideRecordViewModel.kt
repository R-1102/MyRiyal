package com.example.myriyal.utils

import android.content.Context
import com.example.myriyal.screens.records.data.repository.RecordRepositoryImpl
import com.example.myriyal.screens.records.domain.useCases.*
import com.example.myriyal.screens.records.presentation.vmModels.RecordViewModel

/**
 * Provides an instance of [RecordViewModel] manually without using dependency injection (like Hilt).
 *
 * This function wires the data layer (repository), domain layer (use cases), and the presentation layer (view model).
 * It is used in the UI layer (e.g., MyRiyalApp) to create and pass the ViewModel to the navigation graph or screens.
 *
 * Layers:
 * - Data Layer: RecordRepositoryImpl (connects to Room DAO)
 * - Domain Layer: RecordUseCases (business logic grouped together)
 * - Presentation Layer: RecordViewModel (used by the UI)
 *
 * @param context Application context used to initialize Room database access.
 * @return Configured [RecordViewModel] instance ready to use in the UI.
 */
fun provideRecordViewModel(context: Context): RecordViewModel {
    // Create the repository instance that interacts with the Room DAO
    val repo = RecordRepositoryImpl(context)

    // Bundle all use cases into a single container for easier management
    val useCases = RecordUseCases(
        insert = InsertRecordUseCase(repo),
        update = UpdateRecordUseCase(repo),
        delete = DeleteRecordUseCase(repo),
        getAllRecords = GetAllRecordsUseCase(repo),
        getByCategory = GetRecordsByCategoryUseCase(repo),
        getRecordById = GetRecordByIdUseCase(repo)
    )

    // Create the ViewModel and pass in the use case container
    return RecordViewModel(useCases)
}
