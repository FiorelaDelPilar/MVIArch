package com.example.mviarch.updateModule.model

import com.example.mviarch.WineApplication
import com.example.mviarch.commonModule.dataAccess.room.WineDao
import com.example.mviarch.commonModule.entities.Wine

class RoomDatabase {
    private val dao: WineDao by lazy { WineApplication.database.wineDao() }

    fun getWineById(id: Double) = dao.getWineById(id)
    fun updateWined(wine: Wine) = dao.updateWine(wine)
}