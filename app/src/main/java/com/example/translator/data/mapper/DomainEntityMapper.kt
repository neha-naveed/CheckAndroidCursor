package com.example.translator.data.mapper

import com.example.translator.data.local.db.TranslationHistoryEntity
import com.example.translator.domain.model.TranslationHistory
import javax.inject.Inject

class DomainEntityMapper @Inject constructor() : Mapper<TranslationHistoryEntity, TranslationHistory> {
    fun toDomain(entity: TranslationHistoryEntity): TranslationHistory {
        return TranslationHistory(
            id = entity.id,
            sourceText = entity.sourceText,
            translatedText = entity.translatedText,
            sourceLanguage = entity.sourceLanguage,
            targetLanguage = entity.targetLanguage,
            timestamp = entity.timestamp
        )
    }

    fun toEntity(domain: TranslationHistory): TranslationHistoryEntity {
        return TranslationHistoryEntity(
            id = domain.id,
            sourceText = domain.sourceText,
            translatedText = domain.translatedText,
            sourceLanguage = domain.sourceLanguage,
            targetLanguage = domain.targetLanguage,
            timestamp = domain.timestamp
        )
    }
}

