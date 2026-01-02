package com.example.translator.domain.usecase

import com.example.translator.core.bases.usecase.BaseUseCase
import com.example.translator.data.repositories.Repository
import com.example.translator.domain.model.TranslationHistory
import javax.inject.Inject

class SaveTranslationUseCase @Inject constructor(
    private val repository: Repository
) : BaseUseCase<TranslationHistory, Unit>() {
    
    override suspend fun execute(params: TranslationHistory) {
        repository.saveTranslation(params)
    }
}

