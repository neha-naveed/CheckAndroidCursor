package com.example.translator.domain.usecase

import com.example.translator.core.bases.usecase.BaseUseCase
import com.example.translator.core.utils.Resource
import com.example.translator.data.repositories.Repository
import javax.inject.Inject

class TranslateTextUseCase @Inject constructor(
    private val repository: Repository
) : BaseUseCase<TranslateTextUseCase.Params, Resource<String>>() {
    
    data class Params(
        val text: String,
        val sourceLanguage: String,
        val targetLanguage: String
    )

    override suspend fun execute(params: Params): Resource<String> {
        return repository.translateText(
            params.text,
            params.sourceLanguage,
            params.targetLanguage
        )
    }
}

