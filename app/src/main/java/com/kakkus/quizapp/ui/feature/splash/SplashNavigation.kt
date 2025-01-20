package com.kakkus.quizapp.ui.feature.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

data class SplashAction(
    val navigateToHome: () -> Unit,
    val navigateToLogin: () -> Unit,
)

fun NavGraphBuilder.splash(
    actions: SplashAction
) {
    composable("splash") {
        SplashWrapper(
            actions = actions
        )
    }
}