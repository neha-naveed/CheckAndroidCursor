package com.example.translator.ui.feature_main

sealed class MainEvent {
    data class SourceTextChanged(val text: String) : MainEvent()
    data class SourceLanguageSelected(val languageCode: String, val index: Int) : MainEvent()
    data class TargetLanguageSelected(val languageCode: String, val index: Int) : MainEvent()
    object Translate : MainEvent()
    object SwapLanguages : MainEvent()
    object LoadHistory : MainEvent()
    data class DeleteHistoryItem(val id: Long) : MainEvent()
    object ClearHistory : MainEvent()
}

