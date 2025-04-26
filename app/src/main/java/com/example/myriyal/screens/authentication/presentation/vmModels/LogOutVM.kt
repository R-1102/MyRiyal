package com.example.myriyal.screens.authentication.presentation.vmModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myriyal.screens.authentication.domain.useCases.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogOutVM @Inject constructor(
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {

    fun performLogout(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                logOutUseCase.logOut()
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }
}
