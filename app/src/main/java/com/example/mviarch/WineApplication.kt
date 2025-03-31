package com.example.mviarch

import android.app.Application
import androidx.room.Room
import com.example.mviarch.commonModule.dataAccess.room.WineDatabase

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
class WineApplication : Application() {
    companion object {
        lateinit var database: WineDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this,
            WineDatabase::class.java,
            "WineDatabase")
            .build()
    }
}