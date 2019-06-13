package com.example.noteapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class NoteViewModel(application: Application) :AndroidViewModel(application) {
    private val repository: NoteRepository
    val allNotes: LiveData<List<Note>>
    var notesGetted: Note?
    var idGetted: Long?

    init {
        val noteDao = NoteDatabase.getDatabase(application, viewModelScope).noteDao()
        repository = NoteRepository(noteDao)
        allNotes = repository.allNotes
        notesGetted = null
        idGetted = null
    }

    fun insertNote(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun getNoteWithId(id: Long): Note? {
        return repository.getNoteWithId(id)
    }

    fun getNoteIdWithTitle(title: String): Long? {
        return repository.getNoteIdWithTitle(title)
    }

    fun getNoteIdWithDate(dateModified: String): Long? {
        return repository.getNoteIdWithDate(dateModified)
    }

    fun getNoteIdWithColor(color: String): Long? {
        return repository.getNoteIdWithColor(color)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun deleteAllNotes() = viewModelScope.launch {
        repository.deleteAllNotes()
    }
}