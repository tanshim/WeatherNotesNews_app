package com.example.weanotnew.notes.ui.addNote

import android.content.Context
import com.example.weanotnew.BasePresenter
import com.example.weanotnew.BaseView

interface AddNoteContract {

    interface AddNotePresenter: BasePresenter {
        fun saveNote(context: Context, title: String, text: String)
    }

    interface AddNoteView: BaseView<AddNotePresenter> {
        fun onSaveClicked(context: Context): Boolean
    }
}