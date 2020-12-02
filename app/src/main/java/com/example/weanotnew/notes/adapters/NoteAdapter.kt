package com.example.weanotnew.notes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weanotnew.R
import com.example.weanotnew.notes.model.Note
import com.example.weanotnew.util.DATE

class NoteAdapter(var context: Context): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private lateinit var notes: List<Note>

    init {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.tvTitle.text = note.title
        holder.tvText.text = note.text
        holder.tvDate.text = DATE
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(R.id.tvNoteTitle)
        val tvText = itemView.findViewById<TextView>(R.id.tvNoteText)
        val tvDate = itemView.findViewById<TextView>(R.id.tvDate)
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }
}