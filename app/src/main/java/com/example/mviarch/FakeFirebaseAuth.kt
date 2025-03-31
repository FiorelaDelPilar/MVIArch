package com.example.mviarch

import android.net.Uri
import kotlinx.coroutines.delay
import kotlin.random.Random

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
class FakeFirebaseAuth {
    private val _remoteUser = FirebaseUser(
        "1a9n9t0",
        Uri.parse("https://firebasestorage.googleapis.com/v0/b/alain-nicolas-tello-ee2dd." +
                "appspot.com/o/Cursos%20ANT%20Cover.jpg?alt=media&token=5c1b3dac-be98-4106-87ac-dabc64d64eba"),
        "Cursos ANT",
        "alain@cursos.ant",
        "+52 555 892 7731",
        "cursosant",
        "1234")

    suspend fun isValidAuth(): Boolean {
        delay(500)
        val result = _currentUser != null || (!_signOut && Random.nextBoolean())
        if (result) initCurrentUser()
        return result
    }

    suspend fun login(username: String, pin: String): Boolean {
        delay(1_000)
        if (username == _remoteUser.username && pin == _remoteUser.pin){
            initCurrentUser()
        }
        return _currentUser != null
    }

    private fun initCurrentUser() {
        _currentUser = FirebaseUser(
            photoUrl = _remoteUser.photoUrl,
            displayName = _remoteUser.displayName,
            email = _remoteUser.email,
            phone = _remoteUser.phone)
    }

    suspend fun signOut(): Boolean {
        delay(1_000)
        _currentUser = null
        _signOut = true
        return true
    }

    suspend fun getCurrentUser(): FirebaseUser? {
        delay(500)
        return _currentUser
    }

    companion object {
        private var _currentUser: FirebaseUser? = null
        private var _signOut = false
    }
}