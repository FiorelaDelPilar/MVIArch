package com.example.mviarch.commonModule.dataAccess.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
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
@Database(entities = [Wine::class], version = 1)
@TypeConverters(WineConverters::class)
abstract class WineDatabase : RoomDatabase(){
    abstract fun wineDao(): WineDao
}