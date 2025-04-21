package com.example.mviarch.homeModule.model

import com.example.mviarch.R
import com.example.mviarch.commonModule.dataAccess.retrofit.WineService
import com.example.mviarch.commonModule.entities.Wine
import com.example.mviarch.commonModule.utils.Constants
import kotlin.random.Random

class HomeRepository(private val db: RoomDatabase, private val service: WineService) {
    suspend fun getAllWines(): HomeState {
        return try {
            val serverOk = Random.nextBoolean()
            val wines = if (serverOk) service.getRedWines() else listOf()

            if (wines.isNotEmpty()) HomeState.RequestWineSuccess(wines)
            else HomeState.Fail(Constants.EC_REQUEST_NO_WINES, R.string.home_no_wines)
        } catch (e: Exception) {
            HomeState.Fail(Constants.EC_REQUEST, R.string.common_general_fail)
        }
    }

    fun addWine(wine: Wine): HomeState {
        val result = db.addWine(wine)
        return if (result == -1L) {
            HomeState.Fail(Constants.EC_SAVE_WINE, R.string.room_save_fail)
        } else {
            HomeState.AddWineSucess(R.string.room_save_success)
        }
    }
}