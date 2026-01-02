package com.example.translator.data.repositories

import com.example.translator.core.utils.Resource
import com.example.translator.data.local.datasources.LocalDataSource
import com.example.translator.data.remote.datasources.RemoteDataSource
import com.example.translator.domain.model.TranslationHistory
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {
    override suspend fun translateText(
        text: String,
        sourceLanguage: String,
        targetLanguage: String
    ): Resource<String> {
        return try {
            val response = remoteDataSource.translateText(text, sourceLanguage, targetLanguage)
            Resource.Success(response.translatedText)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Translation failed")
        }
    }

    override suspend fun saveTranslation(translation: TranslationHistory) {
        localDataSource.saveTranslation(translation)
    }

    override suspend fun getTranslationHistory(): List<TranslationHistory> {
        return localDataSource.getTranslationHistory()
    }

    override suspend fun deleteTranslation(id: Long) {
        localDataSource.deleteTranslation(id)
    }

    override suspend fun clearAllTranslations() {
        localDataSource.clearAllTranslations()
    }
}

