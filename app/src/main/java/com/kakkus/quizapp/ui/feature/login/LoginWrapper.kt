package com.kakkus.quizapp.ui.feature.login

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kakkus.quizapp.ui.feature.login.navigation.LoginAction

@Composable
fun LoginWrapper(
    viewModel: LoginViewModel = hiltViewModel(),
    actions: LoginAction
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val localContext = LocalContext.current
    val onEvent = viewModel::onEvent
    val effectFlow = viewModel.effect

// Tek seferlik effect'leri dinle
    LaunchedEffect(key1 = true) {
        effectFlow.collect { effect ->
            when (effect) {
                is LoginEffect.NavigateToHome -> {
                    actions.navigateToHome()
                }
                is LoginEffect.ShowError -> {
                    // Snackbar veya Toast gösterebilirsiniz
                    Toast.makeText(localContext, effect.message, Toast.LENGTH_SHORT).show()
                }

                is LoginEffect.NavigateToForgotPassword -> TODO()
            }
        }
    }
    LoginScreen(
        state = state,
        onEvent = onEvent,
        action = actions
    )

}