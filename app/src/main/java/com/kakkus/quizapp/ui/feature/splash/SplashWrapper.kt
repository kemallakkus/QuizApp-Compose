package com.kakkus.quizapp.ui.feature.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SplashWrapper(
    viewModel: SplashViewModel = hiltViewModel(),
    actions: SplashAction
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val onEvent = viewModel::onEvent
    val effectFlow = viewModel.effect

    SplashScreen(
        state = state,
        onEvent = onEvent,
        actions = actions
    )

    // Bu kısımda effect’leri dinleyip yakalıyoruz
    LaunchedEffect(key1 = true) {
        effectFlow.collect { effect ->
            when (effect) {
                SplashEffect.NavigateToHome -> {
                    actions.navigateToHome()
                }
                SplashEffect.NavigateToLogin -> {
                    actions.navigateToLogin()
                }
            }
        }
    }
}