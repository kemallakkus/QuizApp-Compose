package com.kakkus.quizapp.data.model.response

data class BaseResponse<T>(
    val data: T?,
    val message: String
)