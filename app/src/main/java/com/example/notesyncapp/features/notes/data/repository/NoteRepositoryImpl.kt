package com.example.notesyncapp.features.notes.data.repository

import com.example.notesyncapp.features.notes.data.data_source.NoteDao
import com.example.notesyncapp.features.notes.data.data_source.NoteFileStorage
import com.example.notesyncapp.features.notes.data.mapper.toDomain
import com.example.notesyncapp.features.notes.data.mapper.toEntity
import com.example.notesyncapp.features.notes.data.model.NoteEntity
import com.example.notesyncapp.features.notes.domain.model.Note
import com.example.notesyncapp.features.notes.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val noteFileStorage: NoteFileStorage
): NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getNotes().map { entities ->
            entities.toDomain()
        }
    }

    override suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)?.toDomain()
    }

    override suspend fun insertNote(note: Note): Result<Note> =
        withContext(Dispatchers.IO) {
            try {
                val fileResult = noteFileStorage.writeNoteToFile(note.title, note.content)
                if (fileResult.isFailure) {
                    return@withContext Result.failure(fileResult.exceptionOrNull()!!)
                }

                noteDao.insertNote(note.toEntity())

                val savedEntity = note.toEntity().copy(
                    path = fileResult.getOrThrow().absolutePath
                )

                Result.success(savedEntity.toDomain())

            } catch (error: Exception) {
                Result.failure(error)
            }
        }

    override suspend fun deleteNote(note: Note) {
        return noteDao.deleteNote(note.toEntity())
    }
}