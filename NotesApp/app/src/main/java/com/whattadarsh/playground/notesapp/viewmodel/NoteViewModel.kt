package com.whattadarsh.playground.notesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whattadarsh.playground.notesapp.repo.NoteRepository
import com.whattadarsh.playground.notesapp.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteViewModel : ViewModel() {
    private val repository = NoteRepository()
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes

    init {
        fetchNotes()
    }

    fun fetchNotes() {
        viewModelScope.launch {
            _notes.value = repository.getNotes()
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            repository.addNote(note)
            fetchNotes()
        }
    }

    fun deleteNote(noteId: String) {
        viewModelScope.launch {
            repository.deleteNote(noteId)
            fetchNotes()
        }
    }
}
