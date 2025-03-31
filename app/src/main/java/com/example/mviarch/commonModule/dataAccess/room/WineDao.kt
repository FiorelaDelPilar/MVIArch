package com.example.mviarch.commonModule.dataAccess.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mviarch.commonModule.entities.Wine

/****
 * Project: Wines
 * From: com.cursosant.wines
 * Created by Alain Nicolás Tello on 06/02/24 at 20:23
 * All rights reserved 2024.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * And Frogames formación:
 * https://cursos.frogamesformacion.com/pages/instructor-alain-nicolas
 *
 * Coupons on my Website:
 * www.alainnicolastello.com
 ***/
@Dao
interface WineDao {
    @Query("SELECT * FROM WineEntity")
    fun getAllWines(): MutableList<Wine>

    @Query("SELECT * FROM WineEntity WHERE id == :id")
    fun getWineById(id: Double): Wine

    @Insert
    fun addWine(wine: Wine): Long

    @Update
    fun updateWine(wine: Wine): Int

    @Delete
    fun deleteWine(wine: Wine): Int
}