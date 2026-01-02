package com.example.translator.data.local.datasources

import com.example.translator.data.local.db.TranslationHistoryDao
import com.example.translator.data.mapper.DomainEntityMapper
import com.example.translator.domain.model.TranslationHistory
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: TranslationHistoryDao,
    private val mapper: DomainEntityMapper
) : LocalDataSource {
    override suspend fun saveTranslation(translation: TranslationHistory) {
        dao.insertTranslation(mapper.toEntity(translation))
    }

    override suspend fun getTranslationHistory(): List<TranslationHistory> {
        return dao.getAllTranslations().map { mapper.toDomain(it) }
    }

    override suspend fun deleteTranslation(id: Long) {
        dao.deleteTranslation(id)
    }

    override suspend fun clearAllTranslations() {
        dao.clearAll()
    }
}

