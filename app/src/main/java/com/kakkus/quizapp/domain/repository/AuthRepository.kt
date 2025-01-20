package com.kakkus.quizapp.domain.repository

import com.kakkus.quizapp.data.model.response.BaseResponse
import com.kakkus.quizapp.data.model.response.LoginResponse

interface AuthRepository {
    suspend fun login(email: String, password: String): BaseResponse<LoginResponse>
}