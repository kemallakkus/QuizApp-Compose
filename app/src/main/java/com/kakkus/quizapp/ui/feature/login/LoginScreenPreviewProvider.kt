package com.kakkus.quizapp.ui.feature.login

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class LoginScreenPreviewProvider : PreviewParameterProvider<LoginState> {
    override val values: Sequence<LoginState>
        get() = sequenceOf(
            LoginState(
                isLoading = true,
            ),
            LoginState(
                isLoading = false,
            ),
            LoginState(
                isLoading = false,
            ),
        )
}