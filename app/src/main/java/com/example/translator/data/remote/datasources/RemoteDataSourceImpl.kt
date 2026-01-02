package com.example.translator.data.remote.datasources

import com.example.translator.data.remote.network.ApiService
import com.example.translator.data.remote.dto.TranslationResponseDto
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {
    override suspend fun translateText(
        text: String,
        sourceLanguage: String,
        targetLanguage: String
    ): TranslationResponseDto {
        return apiService.translateText(text, sourceLanguage, targetLanguage)
    }
}

