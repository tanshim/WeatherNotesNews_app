package com.example.weanotnew.notes.ui.notes

import android.content.Context
import android.util.Log
import com.example.weanotnew.model.AppDatabase
import com.example.weanotnew.notes.model.Note
import com.example.weanotnew.notes.model.NoteRepository
import javax.inject.Inject


class NotesPresenterImpl(view: NotesContract.NotesView) : NotesContract.NotesPresenter {

    private lateinit var db: AppDatabase
    private lateinit var repository: NoteRepository

    private var view: NotesContract.NotesView? = view

    private fun loadNotes(context: Context) {

        db = AppDatabase.getInstance(context)
        repository = NoteRepository(db)

        val notes: MutableList<Note> = repository.getAllNotes() as MutableList<Note>

        view?.setNotesList(notes)

        Log.d("test01", "loadNotes: end of loadNotes() $notes")
    }

    override fun onViewCreated(context: Context) {
        loadNotes(context)
    }

    override fun onDestroy() {
        this.view = null
    }

    override suspend fun deleteNote(note: Note) {
        repository.deleteNote(note)
    }
}