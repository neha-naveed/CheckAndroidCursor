package com.example.translator.ui.feature_main

import androidx.lifecycle.viewModelScope
import com.example.translator.core.bases.viewmodel.BaseViewModel
import com.example.translator.core.utils.Resource
import com.example.translator.domain.model.TranslationHistory
import com.example.translator.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val translateTextUseCase: TranslateTextUseCase,
    private val saveTranslationUseCase: SaveTranslationUseCase,
    private val getTranslationHistoryUseCase: GetTranslationHistoryUseCase,
    private val deleteTranslationUseCase: DeleteTranslationUseCase
) : BaseViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.TranslateText -> {
                translateText(event.text, event.sourceLang, event.targetLang)
            }
            is MainEvent.LoadHistory -> {
                loadHistory()
            }
            is MainEvent.DeleteHistoryItem -> {
                deleteHistoryItem(event.id)
            }
            is MainEvent.ClearHistory -> {
                clearHistory()
            }
            is MainEvent.SwapLanguages -> {
                swapLanguages(event.sourceLang, event.targetLang)
            }
        }
    }

    private fun translateText(text: String, sourceLang: String, targetLang: String) {
        if (text.isBlank()) return

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            translateTextUseCase(
                TranslateTextUseCase.Params(text, sourceLang, targetLang)
            ).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        val translatedText = result.data
                        _state.value = _state.value.copy(
                            translatedText = translatedText,
                            isLoading = false,
                            error = null
                        )
                        // Save to history
                        saveTranslationUseCase(
                            TranslationHistory(
                                sourceText = text,
                                translatedText = translatedText,
                                sourceLanguage = sourceLang,
                                targetLanguage = targetLang
                            )
                        ).collect {}
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    private fun loadHistory() {
        viewModelScope.launch {
            getTranslationHistoryUseCase(Unit).collect { history ->
                _state.value = _state.value.copy(translationHistory = history)
            }
        }
    }

    private fun deleteHistoryItem(id: Long) {
        viewModelScope.launch {
            deleteTranslationUseCase(id).collect {}
            loadHistory()
        }
    }

    private fun clearHistory() {
        viewModelScope.launch {
            // Implementation for clear all
            loadHistory()
        }
    }

    private fun swapLanguages(sourceLang: String, targetLang: String) {
        _state.value = _state.value.copy(
            sourceLanguage = targetLang,
            targetLanguage = sourceLang,
            sourceText = _state.value.translatedText,
            translatedText = _state.value.sourceText
        )
    }
}

