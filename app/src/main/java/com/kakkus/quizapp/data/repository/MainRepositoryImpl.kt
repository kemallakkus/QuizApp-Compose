package com.kakkus.quizapp.data.repository

import com.kakkus.quizapp.data.source.remote.MainService
import com.kakkus.quizapp.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService
): MainRepository {
}