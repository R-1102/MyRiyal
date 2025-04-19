package com.example.myriyal.screens.records.domain.useCases

import com.example.myriyal.screens.records.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow

// Use case responsible for retrieving the total balance from the record repository
class GetTotalBalanceUseCase(
    private val repository: RecordRepository // Dependency on the repository to access data
) {
    // Operator function that returns a Flow emitting the total balance
    operator fun invoke(): Flow<Double> = repository.getTotalBalance()
}
