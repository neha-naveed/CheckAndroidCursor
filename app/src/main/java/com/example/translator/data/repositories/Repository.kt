package com.example.translator.data.repositories

import com.example.translator.domain.model.TranslationHistory
import com.example.translator.core.utils.Resource

interface Repository {
    suspend fun translateText(
        text: String,
        sourceLanguage: String,
        targetLanguage: String
    ): Resource<String>
    
    suspend fun saveTranslation(translation: TranslationHistory)
    suspend fun getTranslationHistory(): List<TranslationHistory>
    suspend fun deleteTranslation(id: Long)
    suspend fun clearAllTranslations()
}

