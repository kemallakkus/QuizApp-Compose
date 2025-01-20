package com.kakkus.quizapp.common

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
    data class Loading(val isLoading: Boolean = true) : Resource<Nothing>()

}