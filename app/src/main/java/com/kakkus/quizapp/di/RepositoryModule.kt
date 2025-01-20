package com.kakkus.quizapp.di

import com.kakkus.quizapp.data.repository.AuthRepositoryImpl
import com.kakkus.quizapp.data.repository.MainRepositoryImpl
import com.kakkus.quizapp.domain.repository.AuthRepository
import com.kakkus.quizapp.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMainRepository(
        repositoryImpl: MainRepositoryImpl
    ): MainRepository

    @Binds
    @ViewModelScoped
    abstract fun bindQuizRepository(
        repositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}