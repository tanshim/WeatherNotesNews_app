package com.example.weanotnew.notes.model

import com.example.weanotnew.model.AppDatabase
import javax.inject.Inject

class NoteRepository @Inject constructor(private val db: AppDatabase) {

    suspend fun upsertNote(item: Note) = db.noteDao().upsertNote(item)

    suspend fun deleteNote(item: Note) = db.noteDao().deleteNote(item)

    fun getAllNotes() = db.noteDao().getAllNotes()
}