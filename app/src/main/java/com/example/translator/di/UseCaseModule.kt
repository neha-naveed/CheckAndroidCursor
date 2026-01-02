package com.example.translator.di

import com.example.translator.data.repositories.Repository
import com.example.translator.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideTranslateTextUseCase(repository: Repository): TranslateTextUseCase {
        return TranslateTextUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetTranslationHistoryUseCase(repository: Repository): GetTranslationHistoryUseCase {
        return GetTranslationHistoryUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideSaveTranslationUseCase(repository: Repository): SaveTranslationUseCase {
        return SaveTranslationUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteTranslationUseCase(repository: Repository): DeleteTranslationUseCase {
        return DeleteTranslationUseCase(repository)
    }
}

