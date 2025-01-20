package com.kakkus.quizapp.common.extensions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            Result.success(apiCall.invoke())
        } catch (e: HttpException) {
            Result.failure(e) // Sunucu hataları
        } catch (e: IOException) {
            Result.failure(e) // Ağ bağlantı hataları
        } catch (e: Exception) {
            Result.failure(e) // Diğer hatalar
        } as Result<T>
    }
}