package com.example.translator.data.local.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TranslationHistoryDao {
    @Query("SELECT * FROM translation_history ORDER BY timestamp DESC")
    fun getAllTranslationsFlow(): Flow<List<TranslationHistoryEntity>>

    @Query("SELECT * FROM translation_history ORDER BY timestamp DESC")
    suspend fun getAllTranslations(): List<TranslationHistoryEntity>

    @Query("SELECT * FROM translation_history WHERE id = :id")
    suspend fun getTranslationById(id: Long): TranslationHistoryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTranslation(translation: TranslationHistoryEntity): Long

    @Delete
    suspend fun deleteTranslation(translation: TranslationHistoryEntity)

    @Query("DELETE FROM translation_history WHERE id = :id")
    suspend fun deleteTranslation(id: Long)

    @Query("DELETE FROM translation_history")
    suspend fun clearAll()
}

