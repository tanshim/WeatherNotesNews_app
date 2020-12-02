package com.example.weanotnew.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.weanotnew.model.AppDatabase
import com.example.weanotnew.notes.model.NoteDao
import com.example.weanotnew.notes.model.NoteRepository
import com.example.weanotnew.util.DB_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideDb(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: AppDatabase): NoteRepository {
        return NoteRepository(db)
    }

    @Provides
    @Singleton
    fun provideNoteDao(db: AppDatabase): NoteDao{
        return db.noteDao()
    }
}