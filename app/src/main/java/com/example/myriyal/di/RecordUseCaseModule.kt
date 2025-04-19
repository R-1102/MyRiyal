package com.example.myriyal.di


import com.example.myriyal.screens.records.domain.repository.RecordRepository
import com.example.myriyal.screens.records.domain.useCases.DeleteRecordUseCase
import com.example.myriyal.screens.records.domain.useCases.GetAllRecordsUseCase
import com.example.myriyal.screens.records.domain.useCases.GetRecordByIdUseCase
import com.example.myriyal.screens.records.domain.useCases.GetRecordsByCategoryUseCase
import com.example.myriyal.screens.records.domain.useCases.GetTotalBalanceUseCase
import com.example.myriyal.screens.records.domain.useCases.InsertRecordUseCase
import com.example.myriyal.screens.records.domain.useCases.RecordUseCases
import com.example.myriyal.screens.records.domain.useCases.UpdateRecordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module that provides RecordUseCases to be injected into the RecordViewModel.
 */
@Module
@InstallIn(SingletonComponent::class)
object RecordUseCaseModule {

    @Provides
    @Singleton
    fun provideRecordUseCases(repository: RecordRepository): RecordUseCases {
        return RecordUseCases(
            insert = InsertRecordUseCase(repository),
            update = UpdateRecordUseCase(repository),
            delete = DeleteRecordUseCase(repository),
            getAllRecords = GetAllRecordsUseCase(repository),
            getRecordById = GetRecordByIdUseCase(repository),
            getByCategory = GetRecordsByCategoryUseCase(repository),
            getTotalBalance = GetTotalBalanceUseCase(repository)
        )
    }
}
