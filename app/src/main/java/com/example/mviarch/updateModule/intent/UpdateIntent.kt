package com.example.mviarch.updateModule.intent

import com.example.mviarch.commonModule.entities.Wine

sealed class UpdateIntent {
    data class RequestWine(val id: Double) : UpdateIntent()
    data class UpdateWine(val wine: Wine) : UpdateIntent()
}