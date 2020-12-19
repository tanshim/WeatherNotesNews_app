package com.example.weanotnew.notes.model

import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNote(item: Note)

    @Delete
    suspend fun deleteNote(item: Note)

    @Query("SELECT * FROM notes_tb")
    fun getAllNotes(): MutableList<Note>
}