package com.example.weanotnew.notes.ui.notes

import android.content.Context
import android.util.Log
import com.example.weanotnew.model.AppDatabase
import com.example.weanotnew.notes.model.Note
import com.example.weanotnew.notes.model.NoteRepository
import javax.inject.Inject


class NotesPresenterImpl(view: NotesContract.NotesView) : NotesContract.NotesPresenter {

    lateinit var db: AppDatabase
    lateinit var repository: NoteRepository

    private var view: NotesContract.NotesView? = view

    private fun loadNotes(context: Context) {

        db = AppDatabase.getInstance(context)
        repository = NoteRepository(db)
        val testNotes: List<Note> = db.noteDao().getAllNotes()
        val notes: List<Note> = repository.getAllNotes()

        view?.setNotesList(notes)

        Log.d("test01", "loadNotes: end of loadNotes() $notes")
    }

    override fun onViewCreated(context: Context) {
        loadNotes(context)
    }

    override fun onDestroy() {
        this.view = null
    }
}