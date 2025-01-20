package com.kakkus.quizapp.data.source.remote

import com.kakkus.quizapp.common.utils.ApiConstants
import com.kakkus.quizapp.data.model.request.LoginRequest
import com.kakkus.quizapp.data.model.response.BaseResponse
import com.kakkus.quizapp.data.model.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST(ApiConstants.LOGIN)
    suspend fun login(
        @Body request: LoginRequest
    ): BaseResponse<LoginResponse>

    @POST(ApiConstants.REGISTER)
    suspend fun register(
        @Body request: LoginRequest
    ): BaseResponse<Unit>
}