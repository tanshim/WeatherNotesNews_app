package com.example.weanotnew.notes.ui.notes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weanotnew.R
import com.example.weanotnew.notes.adapters.NoteAdapter
import com.example.weanotnew.notes.model.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NotesFragment : Fragment(), NotesContract.NotesView {

    private lateinit var recyclerView: RecyclerView

    private lateinit var presenter: NotesContract.NotesPresenter

    lateinit var notes: List<Note>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_notes, container, false)

        val fabAddNote = view.findViewById<FloatingActionButton>(R.id.fab_add_note)
        fabAddNote.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_notesFragment_to_addNoteFragment)
        }
        
        setPresenter(NotesPresenterImpl(this))

        recyclerView = view.findViewById(R.id.rv_notes)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = NoteAdapter(view.context)

        adapter.notifyDataSetChanged()

        lifecycleScope.launch(Dispatchers.IO) {
            presenter.onViewCreated(view.context)
            if(notes.isNotEmpty()) {
                Log.d("test01", "onCreateView notesList: $notes")
                adapter.setNotes(notes)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
            }
        }

        return view
    }

    override fun setNotesList(notes: List<Note>){
        this.notes = notes
        Log.d("test01", "set notes: ${this.notes}")
    }

    override fun setPresenter(presenter: NotesContract.NotesPresenter) {
        this.presenter = presenter
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}