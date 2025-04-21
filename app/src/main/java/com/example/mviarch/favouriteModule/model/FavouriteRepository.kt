package com.example.mviarch.favouriteModule.model

import com.example.mviarch.R
import com.example.mviarch.commonModule.entities.Wine
import com.example.mviarch.commonModule.utils.Constants

class FavouriteRepository(private val db: RoomDatabase) {
    fun getAllWines(): FavouriteState {
        val result = db.getAllWines()
        return if (result.isNotEmpty()) {
            FavouriteState.RequestWineSuccess(result)
        } else {
            FavouriteState.Fail(Constants.EC_REQUEST, R.string.room_request_fail)
        }
    }

    fun addWine(wine: Wine): FavouriteState {
        val result = db.addWine(wine)
        return if (result == -1L) {
            FavouriteState.Fail(Constants.EC_SAVE_WINE, R.string.room_save_fail)
        } else {
            FavouriteState.AddWineSucess(R.string.room_save_success)
        }
    }

    fun deleteWine(wine: Wine): FavouriteState {
        val result = db.deleteWine(wine)
        return if (result == 0) {
            FavouriteState.Fail(Constants.EC_SAVE_WINE, R.string.room_save_fail)
        } else {
            FavouriteState.DeleteWineSucess(R.string.room_save_success)
        }
    }
}