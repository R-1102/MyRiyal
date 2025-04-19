package com.example.myriyal.screens.Profile.presentattion.vmModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myriyal.core.local.dao.UserDao
import com.example.myriyal.screens.Profile.domain.useCases.CalculateBalanceUseCase
import com.example.myriyal.screens.Profile.domain.useCases.CreateGuestUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userDao: UserDao,
    private val calculateBalanceUseCase: CalculateBalanceUseCase,
    private val createGuestUserUseCase: CreateGuestUserUseCase
) : ViewModel() {

    // UI state for user name
    val userName = mutableStateOf("")

    // UI state for user balance
    val balance = mutableStateOf(0.0)

    // Called once when ViewModel is created
    init {
        viewModelScope.launch {
            // Creates guest user if user table is empty
            createGuestUserUseCase()

            // Loads the user's name and balance from DB
            loadUserData()
        }
    }

    /**
     * Loads the current user (assuming single-user mode),
     * then updates the UI state variables (userName, balance).
     */
    private suspend fun loadUserData() {
        val user = userDao.getAllUsers().firstOrNull()?.firstOrNull()
        if (user != null) {
            userName.value = user.userName
            balance.value = calculateBalanceUseCase()
        }
    }

    /**
     * Updates the user's name in both database and UI state.
     */
    fun updateUserName(newName: String) {
        viewModelScope.launch {
            val user = userDao.getAllUsers().firstOrNull()?.firstOrNull() ?: return@launch
            val updated = user.copy(userName = newName, updatedAt = System.currentTimeMillis())
            userDao.insertUser(updated)
            userName.value = newName
        }
    }

    /**
     * Updates the user's balance in both database and UI state.
     */
    fun updateUserBalance(newBalance: Double) {
        viewModelScope.launch {
            val user = userDao.getAllUsers().firstOrNull()?.firstOrNull() ?: return@launch
            val updated = user.copy(balance = newBalance, updatedAt = System.currentTimeMillis())
            userDao.insertUser(updated)
            balance.value = newBalance
        }
    }
}
