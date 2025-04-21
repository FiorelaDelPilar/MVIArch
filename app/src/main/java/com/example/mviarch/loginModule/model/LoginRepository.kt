package com.example.mviarch.loginModule.model

import com.example.mviarch.R
import com.example.mviarch.commonModule.dataAccess.local.FakeFirebaseAuth
import com.example.mviarch.commonModule.utils.Constants

class LoginRepository(private val auth: FakeFirebaseAuth) {
    suspend fun checkAuth(): LoginState {
        val result = auth.isValidAuth()
        return if (result) {
            LoginState.AuthValid
        } else {
            LoginState.Fail(Constants.EC_AUTH, R.string.login_auth_fail)
        }
    }

    suspend fun login(username: String, pin: String): LoginState {
        val result = auth.login(username, pin)
        return if (result) {
            LoginState.LoginScucess
        } else {
            LoginState.Fail(Constants.EC_LOGIN, R.string.login_login_fail)
        }
    }
}