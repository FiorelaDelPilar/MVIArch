package com.example.mviarch.accountModule.module

import com.example.mviarch.commonModule.entities.FirebaseUser

sealed class AccountState {
    //Estados que conforman las respuestas a través del modelo
    data object Init : AccountState()
    data object ShowProgress : AccountState()
    data object HideProgress : AccountState()
    data object SignOutSuccess : AccountState()
    data class RequestUserSuccess(val user: FirebaseUser) : AccountState()
    data class Fail(val code: Int, val msgRes: Int) : AccountState()

}