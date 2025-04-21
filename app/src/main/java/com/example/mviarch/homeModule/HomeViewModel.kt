package com.example.mviarch.homeModule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mviarch.commonModule.entities.Wine
import com.example.mviarch.favouriteModule.intent.FavouriteIntent
import com.example.mviarch.favouriteModule.model.FavouriteState
import com.example.mviarch.homeModule.intent.HomeIntent
import com.example.mviarch.homeModule.model.HomeRepository
import com.example.mviarch.homeModule.model.HomeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val repository: HomeRepository) : ViewModel() {
    private val _state = MutableStateFlow<HomeState>(HomeState.Init)
    val state: StateFlow<HomeState> = _state

    val channel = Channel<HomeIntent>(Channel.UNLIMITED)

    init {
        setupIntent()
    }

    private fun setupIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow()
                .collect { i ->
                    when (i) {
                        is HomeIntent.RequestWine -> getAllWines()
                        is HomeIntent.AddWine -> addWine(i.wine)
                    }
                }
        }
    }


    private suspend fun getAllWines() {
        _state.value = HomeState.ShowProgress

        try {
            withContext(Dispatchers.IO) {
                _state.value = repository.getAllWines()
            }
        } finally {
            _state.value = HomeState.HideProgress
        }
    }


    private suspend fun addWine(wine: Wine) {
        _state.value = HomeState.ShowProgress

        try {
            withContext(Dispatchers.IO) {
                _state.value = repository.addWine(wine)
            }
        } finally {
            _state.value = HomeState.HideProgress
        }
    }
}