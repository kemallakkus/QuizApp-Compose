package com.kakkus.quizapp.ui.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kakkus.quizapp.common.extensions.navigateReorderBackStack
import com.kakkus.quizapp.common.extensions.navigateTopSingle
import com.kakkus.quizapp.core.AppScreenRoutes
import com.kakkus.quizapp.ui.feature.home.HomeScreen
import com.kakkus.quizapp.ui.feature.home.HomeWrapper
import com.kakkus.quizapp.core.AppScreenRoutes.HomeRoute

// Home rotalarının tanımlandığı fonksiyon

data class HomeAction(
    val navigateToUserMenu: () -> Unit
)

fun NavGraphBuilder.home(
    action: HomeAction,
) {
    composable("home") {
        HomeWrapper(
            action = action,
        )
    }
}

fun NavHostController.navigateToHome(
    argument: HomeRoute = HomeRoute,
    navOptions: NavOptions? = null,
    ) {
    navigateReorderBackStack(argument, navOptions)
}

fun NavHostController.navigateUpFromHome() {
    navigateUp()
}