package com.example.mviarch.loginModule.intent

sealed class LoginIntent {
    data object CheckOut : LoginIntent()
    data class Login(val username: String, val pin: String) : LoginIntent()
}