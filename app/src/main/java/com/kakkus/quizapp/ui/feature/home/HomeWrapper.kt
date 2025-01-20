package com.kakkus.quizapp.ui.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kakkus.quizapp.ui.feature.home.navigation.HomeAction

@Composable
fun HomeWrapper(
    action: HomeAction,
    viewModel: HomeViewModel = hiltViewModel() // Home için ViewModel

) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val localContext = LocalContext.current
    val onEvent = viewModel::onEvent

    // Gerekli bağımlılıkları HomeScreen'e aktar
    HomeScreen(
        state = state,
        onEvent = onEvent,
        action = action
    )
}