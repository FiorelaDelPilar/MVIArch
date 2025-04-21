package com.example.mviarch.favouriteModule.intent

import com.example.mviarch.commonModule.entities.Wine

sealed class FavouriteIntent {
    data object RequestWine : FavouriteIntent()
    data class AddWine(val wine: Wine) : FavouriteIntent()
    data class DeleteWine(val wine: Wine) : FavouriteIntent()
}