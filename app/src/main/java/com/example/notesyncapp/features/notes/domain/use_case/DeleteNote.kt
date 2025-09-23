package com.example.notesyncapp.features.notes.domain.use_case

import com.example.notesyncapp.features.notes.domain.model.Note
import com.example.notesyncapp.features.notes.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}