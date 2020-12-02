package com.example.weanotnew.notes.ui.addNote

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.weanotnew.R
import com.example.weanotnew.notes.model.Note


class AddNoteFragment : Fragment(), AddNoteContract.AddNoteView {

    private lateinit var noteTitle: EditText
    private lateinit var noteText: EditText
    private lateinit var buttonSaveNote: Button
    private lateinit var presenter: AddNoteContract.AddNotePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add_note, container, false)

        setPresenter(AddNotePresenterImpl(this))

        noteTitle = view.findViewById(R.id.editTextNoteTitle)
        noteText = view.findViewById(R.id.editTextNoteText)
        buttonSaveNote = view.findViewById(R.id.buttonSaveNote)

        buttonSaveNote.setOnClickListener {
            if (onSaveClicked(view.context)) {
                Toast.makeText(activity, "Please, enter all fields", Toast.LENGTH_LONG).show()
            } else {
                Navigation.findNavController(view)
                    .navigate(R.id.action_addNoteFragment_to_notesFragment)
            }
        }

        return view
    }

    override fun onSaveClicked(context: Context): Boolean {
        val title = noteTitle.text.toString().trim()
        val text = noteText.text.toString().trim()

        if (title.isNotEmpty() && text.isNotEmpty()) {
            presenter.saveNote(context, title, text)
            return false
        }
        return true
    }

    override fun setPresenter(presenter: AddNoteContract.AddNotePresenter) {
        this.presenter = presenter
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}