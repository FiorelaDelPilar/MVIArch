package com.example.mviarch.favouriteModule.model

import com.example.mviarch.WineApplication
import com.example.mviarch.commonModule.dataAccess.room.WineDao
import com.example.mviarch.commonModule.entities.Wine

class RoomDatabase {
    private val dao: WineDao by lazy { WineApplication.database.wineDao() }

    fun getAllWines() = dao.getAllWines()

    fun addWine(wine: Wine) = dao.addWine(wine)

    fun deleteWine(wine: Wine) = dao.deleteWine(wine)
}