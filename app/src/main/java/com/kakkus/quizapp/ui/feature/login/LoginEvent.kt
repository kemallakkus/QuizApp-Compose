package com.kakkus.quizapp.ui.feature.login

sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    data object PasswordVisibilityChanged : LoginEvent()
    data object LoginButtonClicked : LoginEvent()
}