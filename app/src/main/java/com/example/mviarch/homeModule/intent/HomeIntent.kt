package com.example.mviarch.homeModule.intent

import com.example.mviarch.commonModule.entities.Wine

sealed class HomeIntent {
    data object RequestWine : HomeIntent()
    data class AddWine(val wine: Wine) : HomeIntent()
}