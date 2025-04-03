package com.example.mviarch.accountModule

import androidx.lifecycle.ViewModel
import com.example.mviarch.accountModule.model.AccountRepository
import com.example.mviarch.accountModule.model.AccountState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AccountViewModule(private val repository: AccountRepository) : ViewModel() {
    private val _state = MutableStateFlow<AccountState>(AccountState.Init)
    val state: StateFlow<AccountState> = _state

    private suspend fun getCurrentUser() {
        _state.value = AccountState.ShowProgress

        try {
            _state.value = repository.getCurrentUser()
        } finally {
            _state.value = AccountState.HideProgress
        }
    }

    private suspend fun signOut() {
        _state.value = AccountState.ShowProgress

        try {
            _state.value = repository.signOut()
        } finally {
            _state.value = AccountState.HideProgress
        }
    }
}