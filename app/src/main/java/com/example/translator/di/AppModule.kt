package com.example.translator.di

import android.content.Context
import androidx.room.Room
import com.example.translator.data.local.db.TranslatorDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TranslatorDatabase {
        return Room.databaseBuilder(
            context,
            TranslatorDatabase::class.java,
            "translator_database"
        ).build()
    }

    @Provides
    fun provideTranslationHistoryDao(database: TranslatorDatabase) = database.translationHistoryDao()
}

