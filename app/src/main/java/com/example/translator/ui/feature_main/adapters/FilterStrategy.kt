package com.example.translator.ui.feature_main.adapters

import com.example.translator.core.bases.adapter.helpers.FilterStrategy
import com.example.translator.domain.model.TranslationHistory

class TranslationFilterStrategy : FilterStrategy<TranslationHistory> {
    override fun filter(items: List<TranslationHistory>, query: String): List<TranslationHistory> {
        return items.filter {
            it.sourceText.contains(query, ignoreCase = true) ||
            it.translatedText.contains(query, ignoreCase = true) ||
            it.sourceLanguage.contains(query, ignoreCase = true) ||
            it.targetLanguage.contains(query, ignoreCase = true)
        }
    }
}

