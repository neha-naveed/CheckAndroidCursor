package com.example.translator.ui.feature_main

import com.example.translator.domain.model.Language
import com.example.translator.domain.model.TranslationHistory

data class MainState(
    val sourceText: String = "",
    val translatedText: String = "",
    val sourceLanguage: String = "en",
    val targetLanguage: String = "es",
    val availableLanguages: List<Language> = emptyList(),
    val sourceLanguageIndex: Int = 1, // Default to English (index 1, after "Auto Detect")
    val targetLanguageIndex: Int = 2, // Default to Spanish (index 2)
    val isLoading: Boolean = false,
    val error: String? = null,
    val translationHistory: List<TranslationHistory> = emptyList(),
    val isHistoryVisible: Boolean = false
)

