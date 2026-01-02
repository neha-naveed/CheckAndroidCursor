package com.example.translator.ui.feature_main

import com.example.translator.domain.model.TranslationHistory

data class MainState(
    val sourceText: String = "",
    val translatedText: String = "",
    val sourceLanguage: String = "en",
    val targetLanguage: String = "es",
    val isLoading: Boolean = false,
    val error: String? = null,
    val translationHistory: List<TranslationHistory> = emptyList(),
    val isHistoryVisible: Boolean = false
)

