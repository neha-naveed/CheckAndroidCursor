package com.example.translator.domain.usecase

import com.example.translator.core.bases.usecase.BaseUseCase
import com.example.translator.data.repositories.Repository
import javax.inject.Inject

class DeleteTranslationUseCase @Inject constructor(
    private val repository: Repository
) : BaseUseCase<Long, Unit>() {
    
    override suspend fun execute(params: Long) {
        repository.deleteTranslation(params)
    }
}

