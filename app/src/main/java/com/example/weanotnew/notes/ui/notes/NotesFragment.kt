package com.example.weanotnew.notes.ui.notes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weanotnew.R
import com.example.weanotnew.notes.adapters.NoteAdapter
import com.example.weanotnew.notes.model.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class NotesFragment : Fragment(), NotesContract.NotesView, CoroutineScope {

    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: NotesContract.NotesPresenter
    lateinit var notes: MutableList<Note>

    private lateinit var job: Job

    // context for io thread
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_notes, container, false)

        job = Job()

        val fabAddNote = view.findViewById<FloatingActionButton>(R.id.fab_add_note)
        fabAddNote.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_notesFragment_to_addNoteFragment)
        }

        setPresenter(NotesPresenterImpl(this))

        recyclerView = view.findViewById(R.id.rv_notes)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = NoteAdapter(view.context)

        lifecycleScope.launch(Dispatchers.IO) {
            presenter.onViewCreated(view.context)
            if(notes.isNotEmpty()) {
                Log.d("test01", "onCreateView notesList: $notes")
                adapter.setNotes(notes)
                adapter.notifyDataSetChanged()
                recyclerView.adapter = adapter
            }
        }

        val mIth = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or  ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: ViewHolder,
                    target: ViewHolder
                ): Boolean {
                    return false
                }
                override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                    // remove from adapter
                    val note = adapter.removeAt(viewHolder.adapterPosition)
                    launch {
                        presenter.deleteNote(note)
                    }
                    adapter.notifyItemRemoved(viewHolder.adapterPosition)
                }
            })

        mIth.attachToRecyclerView(recyclerView)

        return view
    }

    override fun setNotesList(notes: MutableList<Note>){
        this.notes = notes
        Log.d("test01", "set notes: ${this.notes}")
    }

    override fun setPresenter(presenter: NotesContract.NotesPresenter) {
        this.presenter = presenter
    }

    override fun onDestroy() {
        job.cancel()
        presenter.onDestroy()
        super.onDestroy()
    }
}