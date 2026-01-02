package com.example.translator.di

import com.example.translator.data.mapper.DomainEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Provides
    @Singleton
    fun provideDomainEntityMapper(): DomainEntityMapper {
        return DomainEntityMapper()
    }
}

