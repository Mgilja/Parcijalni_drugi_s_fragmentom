package com.example.parcijalni_drugi_pokusaj

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(val noteList:List<Note>, val onDelete:(Int) -> Unit):
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textNoteTitle: TextView = itemView.findViewById(R.id.textNoteTitle)
        val textNoteDetails: TextView = itemView.findViewById(R.id.textNoteDetails)
        val buttonDeleteNote: Button = itemView.findViewById(R.id.buttonDeleteNote)

        init {
            buttonDeleteNote.setOnClickListener {
                onDelete(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.note, parent, false)

        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int = noteList.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        holder.textNoteTitle.text = note.title
        holder.textNoteDetails.text = note.details
    }

}



