package com.example.mviarch.commonModule.utils

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
object Constants {
    const val BASE_URL = "https://api.sampleapis.com"
    const val PATH_WINES = "/wines/reds"

    const val DATA_MAIL_TO = "mailto:"
    const val DATA_TEL = "tel:"

    const val ARG_ID = "id"

    // EC = Error Code
    const val EC_AUTH = 200
    const val EC_LOGIN = 210
    const val EC_REQUEST = 300
    const val EC_REQUEST_NO_WINES = 310
    const val EC_REQUEST_USER = 320
    const val EC_SAVE_WINE = 400
    const val EC_GET_WINE = 410
    const val EC_UPDATE_WINE = 420

    const val SHOW = true
    const val HIDE = false
}