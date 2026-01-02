package com.example.translator.ui.feature_main

sealed class MainEvent {
    data class TranslateText(val text: String, val sourceLang: String, val targetLang: String) : MainEvent()
    object LoadHistory : MainEvent()
    data class DeleteHistoryItem(val id: Long) : MainEvent()
    object ClearHistory : MainEvent()
    data class SwapLanguages(val sourceLang: String, val targetLang: String) : MainEvent()
}

