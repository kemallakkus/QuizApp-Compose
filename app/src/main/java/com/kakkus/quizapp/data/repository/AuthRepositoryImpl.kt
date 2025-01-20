package com.kakkus.quizapp.data.repository

import com.kakkus.quizapp.data.model.request.LoginRequest
import com.kakkus.quizapp.data.model.response.BaseResponse
import com.kakkus.quizapp.data.model.response.LoginResponse
import com.kakkus.quizapp.data.source.remote.AuthService
import com.kakkus.quizapp.data.source.remote.MainService
import com.kakkus.quizapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
): AuthRepository {
    override suspend fun login(email: String, password: String): BaseResponse<LoginResponse> {
        return authService.login(LoginRequest(email = email, password = password))
    }
}