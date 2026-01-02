package com.example.translator.domain.usecase

import com.example.translator.core.bases.usecase.BaseUseCase
import com.example.translator.data.repositories.Repository
import com.example.translator.domain.model.TranslationHistory
import javax.inject.Inject

class GetTranslationHistoryUseCase @Inject constructor(
    private val repository: Repository
) : BaseUseCase<Unit, List<TranslationHistory>>() {
    
    override suspend fun execute(params: Unit): List<TranslationHistory> {
        return repository.getTranslationHistory()
    }
}

