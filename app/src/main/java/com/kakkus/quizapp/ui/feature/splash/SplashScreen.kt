package com.kakkus.quizapp.ui.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kakkus.quizapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    state: SplashState,
    onEvent: (SplashEvent) -> Unit,
    actions: SplashAction,
) {
    // Basit bir örnek: Ekranda 'Splash Screen' yazsın
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Splash Screen")
    }

    // Örnek zamanlama ile 2 saniye sonra Login'e geçiş:
    LaunchedEffect(key1 = true) {
        delay(2000)
        actions.navigateToLogin()
    }
}