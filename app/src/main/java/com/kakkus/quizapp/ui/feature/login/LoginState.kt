package com.kakkus.quizapp.ui.feature.login

import androidx.compose.runtime.Immutable

@Immutable
data class LoginState(
    val isLoading: Boolean = true,
    val email: String = "",
    val password: String = "",
    val error: String? = null,
    val isPasswordVisible: Boolean = false,
    val trailingIconVisible: Boolean = false, // İlk karakter girildiğinde trailing icon görünecek
    val isTextFieldsFull: Boolean = false,
    val emailError: String? = null, // Hatalı e-posta için hata mesajı
    val passwordError: String? = null // Hatalı şifre için hata mesajı
)