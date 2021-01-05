package com.example.weanotnew.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weanotnew.notes.model.Note
import com.example.weanotnew.notes.model.NoteDao
import com.example.weanotnew.util.DB_NAME

@Database(entities = [Note::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    companion object {

        private var db: AppDatabase? = null;

        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun noteDao(): NoteDao
}