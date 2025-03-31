package com.example.mviarch.commonModule.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

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
@Entity(tableName = "WineEntity")
data class Wine(val winery: String,
                val wine: String,
                var rating: Rating,
                val location: String,
                val image: String,
                @PrimaryKey val id: Double,
                var isFavorite: Boolean = false)
