package com.example.mviarch.favouriteModule.model

import com.example.mviarch.commonModule.entities.Wine

sealed class FavouriteState {
    data object Init : FavouriteState()
    data object ShowProgress : FavouriteState()
    data object HideProgress : FavouriteState()
    data class RequestWineSuccess(val list: List<Wine>) : FavouriteState()
    data class AddWineSucess(val msgRes: Int) : FavouriteState()
    data class DeleteWineSucess(val msgRes: Int) : FavouriteState()
    data class Fail(val code: Int, val msgRes: Int) : FavouriteState()
}