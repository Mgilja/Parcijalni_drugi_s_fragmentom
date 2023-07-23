package com.example.parcijalni_drugi_pokusaj

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class NotesFragment : Fragment() {

   // private lateinit var recyclerView: RecyclerView
    private lateinit var notesAdapter: NotesAdapter

    private val notesList = mutableListOf<Note>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val  recyclerView:RecyclerView = requireActivity().findViewById(R.id.recyclerView)
        notesAdapter = NotesAdapter(notesList, onDelete)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = notesAdapter

        val editTextNoteTitle = requireView().findViewById<EditText>(R.id.editTextNoteTitle)
        val editTextNoteDetails = requireView().findViewById<EditText>(R.id.editTextNoteDetails)
        val buttonSaveNote = requireView().findViewById<Button>(R.id.buttonSaveNote)

        buttonSaveNote.setOnClickListener {
            val noteTitle = editTextNoteTitle.text.toString()
            val noteDetails = editTextNoteDetails.text.toString()

            if (noteTitle.isNotBlank() && noteDetails.isNotBlank()) {
                val note = Note(noteTitle, noteDetails)
                notesList.add(note)
                notesAdapter.notifyDataSetChanged()


                editTextNoteTitle.text.clear()
                editTextNoteDetails.text.clear()

            } else {

                if (noteTitle.isBlank()) {
                    editTextNoteTitle.error = "Enter the note title"
                }
                if (noteDetails.isBlank()) {
                    editTextNoteDetails.error = "Enter the note details"
                }
            }
        }
    }

    private val onDelete: (Int) -> Unit = { position ->
        notesList.removeAt(position)
        notesAdapter.notifyItemRemoved(position)
    }
}
