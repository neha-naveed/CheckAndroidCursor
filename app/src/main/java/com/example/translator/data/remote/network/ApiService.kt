package com.example.translator.data.remote.network

import com.example.translator.data.remote.dto.TranslationResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("translate")
    suspend fun translateText(
        @Query("text") text: String,
        @Query("source") sourceLanguage: String,
        @Query("target") targetLanguage: String
    ): TranslationResponseDto
}

