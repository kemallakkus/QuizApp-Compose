package com.kakkus.quizapp.domain.usecases

import com.kakkus.quizapp.common.Resource
import com.kakkus.quizapp.data.model.response.LoginResponse
import com.kakkus.quizapp.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, password: String): Flow<Resource<LoginResponse>> = flow {
        emit(Resource.Loading())
        try {
            // Repository’den yanıt alıyoruz
            val response = authRepository.login(email, password) // BaseResponse<LoginResponse>

            // Hata mantığınız (örneğin backend "success" kontrolü vs.)
            if (response.data != null) {
                emit(Resource.Success(response.data))
            } else {
                emit(Resource.Error(response.message))
            }
        } catch (e: Exception) {
            emit(Resource.Error(
                message = e.localizedMessage ?: "Unknown Error",
            ))
        }
    }.flowOn(Dispatchers.IO)
}