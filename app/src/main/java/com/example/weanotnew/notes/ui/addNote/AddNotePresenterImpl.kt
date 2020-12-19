package com.example.weanotnew.notes.ui.addNote

import android.content.Context
import com.example.weanotnew.model.AppDatabase
import com.example.weanotnew.notes.model.Note
import com.example.weanotnew.notes.model.NoteRepository
import com.example.weanotnew.util.convertTimestampToTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddNotePresenterImpl(view: AddNoteContract.AddNoteView): AddNoteContract.AddNotePresenter {

    lateinit var db: AppDatabase
    lateinit var repository: NoteRepository

    private var view: AddNoteContract.AddNoteView? = view

    override fun saveNote(context: Context, title: String, text: String) {

        db = AppDatabase.getInstance(context)
        repository = NoteRepository(db)
        val timestamp = System.currentTimeMillis()
        val createTime = convertTimestampToTime(timestamp)

        GlobalScope.launch(Dispatchers.IO) {
            repository.upsertNote(Note(title, text, createTime))
        }
    }

    override fun onDestroy() {
        this.view = null
    }

}