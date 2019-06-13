package com.example.noteapp

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDAO) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    @WorkerThread
    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note)
    }

    @WorkerThread
    fun getNoteWithId(id: Long): Note? {
        return noteDao.getNoteWithId(id)
    }

    @WorkerThread
    fun getNoteIdWithTitle(title: String): Long? {
        return noteDao.getNoteIdWithTitle(title)
    }

    @WorkerThread
    fun getNoteIdWithDate(dateModified: String): Long? {
        return noteDao.getNoteIdWithDate(dateModified)
    }

    @WorkerThread
    fun getNoteIdWithColor(color: String): Long? {
        return noteDao.getNoteIdWithColor(color)
    }

    @WorkerThread
    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    @WorkerThread
    suspend fun deleteAllNotes() {
        noteDao.deleteAllNotes()
    }
}
