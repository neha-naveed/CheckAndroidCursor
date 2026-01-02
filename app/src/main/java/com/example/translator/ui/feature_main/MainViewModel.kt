package com.example.translator.ui.feature_main

import androidx.lifecycle.viewModelScope
import com.example.translator.core.bases.viewmodel.BaseViewModel
import com.example.translator.core.utils.Resource
import com.example.translator.data.local.LanguageProvider
import com.example.translator.domain.model.TranslationHistory
import com.example.translator.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val translateTextUseCase: TranslateTextUseCase,
    private val saveTranslationUseCase: SaveTranslationUseCase,
    private val getTranslationHistoryUseCase: GetTranslationHistoryUseCase,
    private val deleteTranslationUseCase: DeleteTranslationUseCase,
    private val languageProvider: LanguageProvider
) : BaseViewModel() {

    private val _state = MutableStateFlow(
        MainState(
            availableLanguages = languageProvider.getSupportedLanguages()
        )
    )
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        // Load history on init
        loadHistory()
    }

    fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.SourceTextChanged -> {
                _state.value = _state.value.copy(sourceText = event.text)
            }
            is MainEvent.SourceLanguageSelected -> {
                _state.value = _state.value.copy(
                    sourceLanguage = event.languageCode,
                    sourceLanguageIndex = event.index
                )
            }
            is MainEvent.TargetLanguageSelected -> {
                _state.value = _state.value.copy(
                    targetLanguage = event.languageCode,
                    targetLanguageIndex = event.index
                )
            }
            is MainEvent.Translate -> {
                translateCurrentText()
            }
            is MainEvent.SwapLanguages -> {
                swapLanguages()
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
        }
    }

    private fun translateCurrentText() {
        val currentState = _state.value
        val text = currentState.sourceText.trim()
        
        if (text.isBlank()) {
            _state.value = currentState.copy(error = "Please enter text to translate")
            return
        }

        val sourceLang = if (currentState.sourceLanguage == "auto") {
            "auto"
        } else {
            currentState.sourceLanguage
        }
        val targetLang = currentState.targetLanguage

        if (sourceLang != "auto" && sourceLang == targetLang) {
            _state.value = currentState.copy(error = "Source and target languages cannot be the same")
            return
        }

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
                        launch {
                            saveTranslationUseCase(
                                TranslationHistory(
                                    sourceText = text,
                                    translatedText = translatedText,
                                    sourceLanguage = sourceLang,
                                    targetLanguage = targetLang
                                )
                            ).first()
                            loadHistory()
                        }
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = result.message ?: "Translation failed"
                        )
                    }
                    is Resource.Loading -> {
                        // Loading state is already set before the call
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
            deleteTranslationUseCase(id).first()
            loadHistory()
        }
    }

    private fun clearHistory() {
        viewModelScope.launch {
            // Clear all translations
            _state.value.translationHistory.forEach { history ->
                deleteTranslationUseCase(history.id).first()
            }
            loadHistory()
        }
    }

    private fun swapLanguages() {
        val currentState = _state.value
        val sourceLangIndex = currentState.sourceLanguageIndex
        val targetLangIndex = currentState.targetLanguageIndex
        
        _state.value = currentState.copy(
            sourceLanguage = currentState.targetLanguage,
            targetLanguage = currentState.sourceLanguage,
            sourceLanguageIndex = targetLangIndex,
            targetLanguageIndex = sourceLangIndex,
            sourceText = currentState.translatedText,
            translatedText = currentState.sourceText
        )
    }
}

