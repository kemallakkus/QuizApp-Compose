package com.kakkus.quizapp.ui.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakkus.quizapp.data.source.local.TokenDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val tokenDataStore: TokenDataStore
) : ViewModel() {

    private val _state = MutableStateFlow(SplashState())
    val state = _state.asStateFlow()

    private val _effect = Channel<SplashEffect>(Channel.UNLIMITED)
    val effect = _effect.receiveAsFlow()

    init {
        checkToken()
    }

    private fun checkToken() {
        viewModelScope.launch {
            val token = tokenDataStore.getToken()
            // Token null değilse Home’a, null ise Login’e yönlendirmek için effect tetikle
            if (token.isNullOrEmpty()) {
                _effect.send(SplashEffect.NavigateToLogin)
            } else {
                _effect.send(SplashEffect.NavigateToHome)
            }
        }
    }

    fun onEvent(event: SplashEvent) {
        when (event) {
            // Gelen event’e göre state veya effect güncelleyebilirsiniz.
            else -> {}
        }
    }
}