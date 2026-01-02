package com.example.translator.data.local

import com.example.translator.domain.model.Language
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageProvider @Inject constructor() {
    fun getSupportedLanguages(): List<Language> {
        return listOf(
            Language("auto", "Auto Detect"),
            Language("en", "English"),
            Language("es", "Spanish"),
            Language("fr", "French"),
            Language("de", "German"),
            Language("it", "Italian"),
            Language("pt", "Portuguese"),
            Language("ru", "Russian"),
            Language("ja", "Japanese"),
            Language("ko", "Korean"),
            Language("zh", "Chinese"),
            Language("ar", "Arabic"),
            Language("hi", "Hindi"),
            Language("tr", "Turkish"),
            Language("pl", "Polish"),
            Language("nl", "Dutch"),
            Language("sv", "Swedish"),
            Language("da", "Danish"),
            Language("no", "Norwegian"),
            Language("fi", "Finnish"),
            Language("cs", "Czech"),
            Language("ro", "Romanian"),
            Language("hu", "Hungarian"),
            Language("el", "Greek"),
            Language("he", "Hebrew"),
            Language("th", "Thai"),
            Language("vi", "Vietnamese"),
            Language("id", "Indonesian"),
            Language("ms", "Malay"),
            Language("uk", "Ukrainian")
        )
    }

    fun getLanguageByCode(code: String): Language? {
        return getSupportedLanguages().find { it.code == code }
    }

    fun getLanguageName(code: String): String {
        return getLanguageByCode(code)?.name ?: code
    }
}

