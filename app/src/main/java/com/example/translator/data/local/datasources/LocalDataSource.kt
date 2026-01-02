package com.example.translator.data.local.datasources

import com.example.translator.domain.model.TranslationHistory

interface LocalDataSource {
    suspend fun saveTranslation(translation: TranslationHistory)
    suspend fun getTranslationHistory(): List<TranslationHistory>
    suspend fun deleteTranslation(id: Long)
    suspend fun clearAllTranslations()
}

