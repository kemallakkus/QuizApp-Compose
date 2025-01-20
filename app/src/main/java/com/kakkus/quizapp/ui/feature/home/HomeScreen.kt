package com.kakkus.quizapp.ui.feature.home

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.kakkus.quizapp.ui.feature.home.navigation.HomeAction
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    onEvent: (HomeEvent) -> Unit,
    state: HomeState,
    action: HomeAction,
) {
    Text(
        text = "Home Screen",
    )
}