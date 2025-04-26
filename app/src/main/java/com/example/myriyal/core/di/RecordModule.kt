package com.example.myriyal.core.di

import com.example.myriyal.screens.records.data.repository.RecordRepositoryImpl
import com.example.myriyal.screens.records.domain.repository.RecordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RecordModule {

    @Binds
    abstract fun bindRecordRepository(
        impl: RecordRepositoryImpl
    ): RecordRepository
}
