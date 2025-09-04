package com.example.notesyncapp.features.notes.domain.use_case

import com.example.notesyncapp.features.notes.domain.model.Note
import com.example.notesyncapp.features.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class getNotes(
    private val repository: NoteRepository
) {
    operator fun invoke(): Flow<List<Note>> {

    }
}