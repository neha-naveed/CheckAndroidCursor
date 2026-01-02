package com.example.core.bases.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseUseCase<in P, R> {
    suspend operator fun invoke(params: P): Flow<R> = flow {
        emit(execute(params))
    }

    protected abstract suspend fun execute(params: P): R
}

