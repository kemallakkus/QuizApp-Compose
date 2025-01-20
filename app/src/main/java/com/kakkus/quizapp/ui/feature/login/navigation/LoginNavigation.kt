package com.kakkus.quizapp.ui.feature.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.kakkus.quizapp.common.extensions.navigateTopSingle
import com.kakkus.quizapp.ui.feature.login.LoginWrapper
import com.kakkus.quizapp.core.AppScreenRoutes.LoginRoute

data class LoginAction(
    val navigateToRegister: () -> Unit,
    val navigateToHome: () -> Unit,
    val navigateToForgotPassword: () -> Unit,
)

fun NavGraphBuilder.login(
    actions: LoginAction,
) {
    composable("login") {
        LoginWrapper(
            actions = actions
        )
    }
}

fun NavController.navigateToLogin(
    argument: LoginRoute = LoginRoute(),
    navOptions: NavOptions? = null
) {
    navigateTopSingle(argument, navOptions)
}

fun NavHostController.navigateUpFromLogin() {
    navigateUp()
}