package com.kakkus.quizapp.ui.feature.home

import androidx.lifecycle.ViewModel
import com.kakkus.quizapp.ui.feature.login.LoginEvent
import com.kakkus.quizapp.ui.feature.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (

): ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun onEvent(event: HomeEvent) {

    }
}