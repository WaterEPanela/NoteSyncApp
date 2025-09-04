package com.example.notesyncapp.features.notes.data.repository

import com.example.notesyncapp.features.notes.data.data_source.NoteDao
import com.example.notesyncapp.features.notes.data.mapper.toDomain
import com.example.notesyncapp.features.notes.data.mapper.toEntity
import com.example.notesyncapp.features.notes.domain.model.Note
import com.example.notesyncapp.features.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(
    private val noteDao: NoteDao
): NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes().map { entities ->
            entities.toDomain()
        }
    }

    override suspend fun getNotebyId(id: Int): Note? {
        return noteDao.getNoteById(id)?.toDomain()
    }

    override suspend fun insertNote(note: Note) {
        return noteDao.insertNote(note.toEntity())
    }

    override suspend fun deleteNote(note: Note) {
        return noteDao.deleteNote(note.toEntity())
    }
}