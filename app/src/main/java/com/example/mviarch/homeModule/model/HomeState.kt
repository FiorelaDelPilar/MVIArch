package com.example.mviarch.homeModule.model

import com.example.mviarch.commonModule.entities.Wine

sealed class HomeState {
    data object Init : HomeState()
    data object ShowProgress : HomeState()
    data object HideProgress : HomeState()
    data class RequestWineSuccess(val list: List<Wine>) : HomeState()
    data class AddWineSucess(val msgRes: Int) : HomeState()
    data class Fail(val code: Int, val msgRes: Int) : HomeState()
}