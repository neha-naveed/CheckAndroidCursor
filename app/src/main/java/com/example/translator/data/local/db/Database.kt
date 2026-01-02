package com.example.translator.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TranslationHistoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TranslatorDatabase : RoomDatabase() {
    abstract fun translationHistoryDao(): TranslationHistoryDao
}

