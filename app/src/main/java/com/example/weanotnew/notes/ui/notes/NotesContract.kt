package com.example.weanotnew.notes.ui.notes

import android.content.Context
import com.example.weanotnew.BasePresenter
import com.example.weanotnew.BaseView
import com.example.weanotnew.notes.model.Note

interface NotesContract {

    interface NotesPresenter: BasePresenter {
        fun onViewCreated(context: Context)
        suspend fun deleteNote(note: Note)
    }

    interface NotesView: BaseView<NotesPresenter> {
        fun setNotesList(notes: MutableList<Note>)
    }

}