package com.example.mviarch.loginModule.model

sealed class LoginState {
    data object Init : LoginState()
    data object ShowProgress : LoginState()
    data object HideProgress : LoginState()
    data class Fail(val code: Int, val msgRes: Int) : LoginState()

    data object AuthValid : LoginState()
    data object LoginScucess : LoginState()

}