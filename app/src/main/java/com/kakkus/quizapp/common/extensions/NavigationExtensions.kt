package com.kakkus.quizapp.common.extensions

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

fun <T : Any> NavController.navigateTopSingle(
    route: T,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
) {
    val defaultNavOptionsBuilder = NavOptions.Builder()
        .setLaunchSingleTop(true)

    navOptions?.let { userNavOptions ->
        defaultNavOptionsBuilder.apply {
            setEnterAnim(userNavOptions.enterAnim)
            setExitAnim(userNavOptions.exitAnim)
            setPopEnterAnim(userNavOptions.popEnterAnim)
            setPopExitAnim(userNavOptions.popExitAnim)
            setPopUpTo(userNavOptions.popUpToId, userNavOptions.isPopUpToInclusive())
        }
    }
    return navigate(route, defaultNavOptionsBuilder.build(), navigatorExtras)
}

fun <T : Any> NavController.navigateReorderBackStack(
    route: T,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
) {
    this@navigateReorderBackStack.navigate(route) {
        launchSingleTop = true
        popUpTo(this@navigateReorderBackStack.graph.findStartDestination().id) {
            saveState = true
        }
        restoreState = true
    }
}