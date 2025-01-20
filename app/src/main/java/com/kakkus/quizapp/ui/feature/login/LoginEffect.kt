package com.kakkus.quizapp.ui.feature.login

sealed class LoginEffect {
    data object NavigateToHome : LoginEffect()
    data class NavigateToForgotPassword(val username: String) : LoginEffect()
    data class ShowError(val message: String) : LoginEffect()


}