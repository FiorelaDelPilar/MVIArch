package com.example.mviarch.commonModule.entities

import android.net.Uri

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
data class FirebaseUser(val uid: String = "",
                        val photoUrl: Uri? = null,
                        val displayName: String = "",
                        val email: String = "",
                        val phone: String = "",
                        val username: String = "",
                        val pin: String = "")
