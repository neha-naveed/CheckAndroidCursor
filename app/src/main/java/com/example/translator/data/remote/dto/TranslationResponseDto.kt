package com.example.translator.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TranslationResponseDto(
    @SerializedName("translatedText")
    val translatedText: String,
    @SerializedName("sourceLanguage")
    val sourceLanguage: String,
    @SerializedName("targetLanguage")
    val targetLanguage: String
)

