package com.example.translator.di

import com.example.translator.data.local.datasources.LocalDataSource
import com.example.translator.data.local.datasources.LocalDataSourceImpl
import com.example.translator.data.remote.datasources.RemoteDataSource
import com.example.translator.data.remote.datasources.RemoteDataSourceImpl
import com.example.translator.data.repositories.Repository
import com.example.translator.data.repositories.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ): RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(
        localDataSourceImpl: LocalDataSourceImpl
    ): LocalDataSource

    @Binds
    @Singleton
    abstract fun bindRepository(
        repositoryImpl: RepositoryImpl
    ): Repository
}

