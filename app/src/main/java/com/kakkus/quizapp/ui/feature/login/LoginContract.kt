package com.kakkus.quizapp.ui.feature.login

object LoginContract {
    data class UiState (
        val isLoading: Boolean = false,
    )

    sealed class UiAction

    sealed class UiEffect

}