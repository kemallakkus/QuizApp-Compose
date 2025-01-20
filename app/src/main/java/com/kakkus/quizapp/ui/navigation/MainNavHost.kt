package com.kakkus.quizapp.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kakkus.quizapp.core.AppScreenRoutes
import com.kakkus.quizapp.ui.feature.home.navigation.HomeAction
import com.kakkus.quizapp.ui.feature.home.navigation.home
import com.kakkus.quizapp.ui.feature.login.LoginScreen
import com.kakkus.quizapp.ui.feature.login.navigation.LoginAction
import com.kakkus.quizapp.ui.feature.login.navigation.login
import com.kakkus.quizapp.ui.feature.register.RegisterScreen
import com.kakkus.quizapp.ui.feature.splash.SplashAction
import com.kakkus.quizapp.ui.feature.splash.SplashScreen
import com.kakkus.quizapp.ui.feature.splash.splash

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: AppScreenRoutes,
) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    )  {
        splash(
            actions = SplashAction(
                navigateToLogin = {
                    navController.navigate("login") {
                        popUpTo("splash") {
                            inclusive = true
                        }
                    }
                },
                navigateToHome = {
                    navController.navigate("home") {
                        popUpTo("splash") {
                            inclusive = true
                        }
                    }
                }
            )
        )
        home(
            action = HomeAction(
                navigateToUserMenu = {}
            )
        )
        login(
            actions = LoginAction(
                navigateToRegister = {
                    navController.navigate("register")
                },
                navigateToHome = {
                    navController.navigate("home")
                },
                navigateToForgotPassword = {}
            )
        )
    }
}