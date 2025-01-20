package com.kakkus.quizapp.core

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
sealed interface AppScreenRoutes {
    @Serializable
    @Keep
    data class LoginRoute(
        val username: String = ""
    ) {
        val route = LoginRoute::class.qualifiedName + "/{username}"
    }

    @Serializable
    @Keep
    data object HomeRoute : AppScreenRoutes {
        val route = HomeRoute::class.qualifiedName
    }

    @Serializable
    @Keep
    data object SplashRoute : AppScreenRoutes {
        val route = SplashRoute::class.qualifiedName
    }

}