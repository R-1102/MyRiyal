package com.example.myriyal.screens.authentication.presentation.vmModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myriyal.screens.authentication.domain.useCases.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogOutVM @Inject constructor(
    private val logOutUseCase: LogOutUseCase
):ViewModel(){

    private val eventsChannel = Channel<AllEvents>()
    val allEventsFlow = eventsChannel.receiveAsFlow()

    fun performLogout() = viewModelScope.launch {
        try {
            logOutUseCase.logOut()
            eventsChannel.send(AllEvents.Message("Logged out successfully"))
        } catch (e: Exception) {
            eventsChannel.send(AllEvents.Error(e.localizedMessage ?: "Logout failed"))
        }
    }

    sealed class AllEvents {
        data class Message(val message: String) : AllEvents()
        data class Error(val error: String) : AllEvents()
    }

}