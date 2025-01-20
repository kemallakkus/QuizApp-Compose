package com.kakkus.quizapp.ui.feature.splash

sealed interface SplashEffect {
    data object NavigateToHome : SplashEffect
    data object NavigateToLogin : SplashEffect
}