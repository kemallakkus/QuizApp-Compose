package com.kakkus.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.kakkus.quizapp.core.AppScreenRoutes
import com.kakkus.quizapp.data.source.local.TokenDataStore
import com.kakkus.quizapp.ui.navigation.MainNavHost
import com.kakkus.quizapp.ui.theme.QuizAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tokenManager = TokenDataStore(applicationContext)

        setContent {
            QuizAppTheme {
                val navController = rememberNavController()
                val startDestination = AppScreenRoutes.SplashRoute
                MainNavHost(navController, startDestination = startDestination)
            }
        }
    }
}