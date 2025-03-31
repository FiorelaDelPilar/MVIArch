package com.example.mviarch

import androidx.room.TypeConverter
import com.google.gson.Gson

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
class WineConverters {
    @TypeConverter
    fun fromJsonStr(value: String?): Rating? {
        return value?.let { Gson().fromJson(it, Rating::class.java) }
    }

    @TypeConverter
    fun fromRating(value: Rating?): String? {
        return value?.let { Gson().toJson(it) }
    }
}