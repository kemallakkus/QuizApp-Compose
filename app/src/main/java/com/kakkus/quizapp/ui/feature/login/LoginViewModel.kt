package com.kakkus.quizapp.ui.feature.login

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakkus.quizapp.common.Resource
import com.kakkus.quizapp.data.model.response.BaseResponse
import com.kakkus.quizapp.data.model.response.LoginResponse
import com.kakkus.quizapp.data.source.local.TokenDataStore
import com.kakkus.quizapp.domain.usecases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val tokenDataStore: TokenDataStore
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _effect by lazy { Channel<LoginEffect>() }
    val effect: Flow<LoginEffect> by lazy { _effect.receiveAsFlow() }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                updateUiState {
                    copy(
                        email = event.email,
                    )
                }
                validateFields()
            }

            is LoginEvent.PasswordChanged -> {
                updateUiState {
                    copy(
                        password = event.password,
                        trailingIconVisible = event.password.isNotEmpty(),
                    )
                }
                validateFields()
            }

            LoginEvent.PasswordVisibilityChanged -> {
                    togglePasswordVisibility()
            }

            LoginEvent.LoginButtonClicked -> {
                performValidation()
            }
        }
    }

    fun login() {
        viewModelScope.launch {
            loginUseCase(state.value.email, state.value.password).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        // LoginResponse geldi
                        val loginResponse = resource.data
                        // Örneğin token’ı kaydetmek
                        loginResponse.token?.let { token ->
                            tokenDataStore.saveToken(token)
                        }
                        // Loading false yapın
                        updateUiState { copy(isLoading = false) }
                        // Bir effect ile Home ekranına geçin
                        emitUiEffect(LoginEffect.NavigateToHome)
                    }
                    is Resource.Error -> {
                        // Hata mesajı veya effect
                        updateUiState { copy(isLoading = false) }
                        // İsterseniz state içine error koyabilirsiniz
                        updateUiState { copy(emailError = resource.message) }
                        // Ya da bir snackBar/diyalog efekti
                        // emitUiEffect(LoginEffect.ShowError(resource.message))
                    }
                    is Resource.Loading -> {
                        // Ekranda bir loading göstermek isterseniz
                        updateUiState { copy(isLoading = true) }
                    }
                }
            }
        }
    }

    private fun togglePasswordVisibility() {
        updateUiState { copy(isPasswordVisible = !isPasswordVisible) }
    }

    private fun validateFields() {
        val isFull = state.value.email.isNotEmpty() && state.value.password.isNotEmpty()
        updateUiState { copy(isTextFieldsFull = isFull) }
    }

    private fun performValidation() {
        val emailError = validateEmail(state.value.email)
        val passwordError = validatePassword(state.value.password)

        updateUiState {
            copy(
                emailError = emailError,
                passwordError = passwordError
            )
        }

        if (emailError == null && passwordError == null) {
            // Tüm doğrulamalar geçti, login işlemini başlat
            login()
        }
    }

    private fun validateEmail(email: String): String? {
        return if (email.isEmpty()) {
            "Email cannot be empty"
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            "Invalid email address"
        } else {
            null // Geçerli e-posta
        }
    }

    private fun validatePassword(password: String): String? {
        return if (password.isEmpty()) {
            "Password cannot be empty"
        } else if (password.length < 6) {
            "Password must be at least 6 characters"
        } else {
            null // Geçerli şifre
        }
    }

    private fun updateUiState(block: LoginState.() -> LoginState) {
        _state.update(block)
    }

    private suspend fun emitUiEffect(loginEffect: LoginEffect) {
        _effect.send(loginEffect)
    }
}

