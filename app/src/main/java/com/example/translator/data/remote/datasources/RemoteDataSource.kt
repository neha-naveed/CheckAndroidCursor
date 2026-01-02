package com.example.translator.data.remote.datasources

import com.example.translator.data.remote.dto.TranslationResponseDto

interface RemoteDataSource {
    suspend fun translateText(
        text: String,
        sourceLanguage: String,
        targetLanguage: String
    ): TranslationResponseDto
}

