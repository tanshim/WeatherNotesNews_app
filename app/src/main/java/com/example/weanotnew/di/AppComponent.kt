package com.example.weanotnew.di

import com.example.weanotnew.notes.ui.addNote.AddNotePresenterImpl
import com.example.weanotnew.notes.ui.notes.NotesFragment
import com.example.weanotnew.notes.ui.notes.NotesPresenterImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(target: NotesPresenterImpl)
    fun inject(notesFragment: NotesFragment)
    fun inject(target: AddNotePresenterImpl)

}