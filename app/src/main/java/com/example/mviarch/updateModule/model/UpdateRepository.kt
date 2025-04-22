package com.example.mviarch.updateModule.model

import com.example.mviarch.R
import com.example.mviarch.commonModule.entities.Wine
import com.example.mviarch.commonModule.utils.Constants

class UpdateRepository(private val db: RoomDatabase) {
    fun requestWine(id: Double): UpdateState {
        return try {
            val result = db.getWineById(id)
            UpdateState.RequestWineSuccess(result)
        } catch (e: Exception) {
            UpdateState.Fail(Constants.EC_GET_WINE, R.string.room_request_fail)
        }
    }

    fun updateWine(wine: Wine): UpdateState {
        val result = db.updateWined(wine)
        return if (result == 0) {
            UpdateState.Fail(Constants.EC_UPDATE_WINE, R.string.room_update_fail)
        } else {
            UpdateState.UpdateWineSuccess
        }
    }
}