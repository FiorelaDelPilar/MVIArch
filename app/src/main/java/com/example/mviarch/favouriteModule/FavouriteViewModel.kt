package com.example.mviarch.favouriteModule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mviarch.commonModule.entities.Wine
import com.example.mviarch.favouriteModule.intent.FavouriteIntent
import com.example.mviarch.favouriteModule.model.FavouriteRepository
import com.example.mviarch.favouriteModule.model.FavouriteState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class FavouriteViewModel(private val repository: FavouriteRepository) : ViewModel() {
    private val _state = MutableStateFlow<FavouriteState>(FavouriteState.Init)
    val state: StateFlow<FavouriteState> = _state

    val channel = Channel<FavouriteIntent>(Channel.UNLIMITED)

    init {
        setupIntent()
    }

    private fun setupIntent() {
        viewModelScope.launch {
            channel.consumeAsFlow()
                .collect { i ->
                    when (i) {
                        is FavouriteIntent.RequestWine -> getAllWines()
                        is FavouriteIntent.AddWine -> addWine(i.wine)
                        is FavouriteIntent.DeleteWine -> deleteWine(i.wine)
                    }
                }
        }
    }

    private fun getAllWines() {
        _state.value = FavouriteState.ShowProgress

        try {//TODO: withContext...
            _state.value = repository.getAllWines()
        } finally {
            _state.value = FavouriteState.HideProgress
        }
    }


    private fun addWine(wine: Wine) {
        _state.value = FavouriteState.ShowProgress

        try {
            _state.value = repository.addWine(wine)
        } finally {
            _state.value = FavouriteState.HideProgress
        }
    }


    private fun deleteWine(wine: Wine) {
        _state.value = FavouriteState.ShowProgress

        try {
            _state.value = repository.deleteWine(wine)
        } finally {
            _state.value = FavouriteState.HideProgress
        }

    }
}