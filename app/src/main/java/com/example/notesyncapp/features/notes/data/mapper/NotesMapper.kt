package com.example.notesyncapp.features.notes.data.mapper

import com.example.notesyncapp.features.notes.data.model.NoteEntity
import com.example.notesyncapp.features.notes.domain.model.Note


fun NoteEntity.toDomain(): Note = Note(
    title = title,
    path = path,
    id = id,
    createdAt = createdAt,
    updatedAt = updatedAt,
    syncedAt = syncedAt,
    isLocked = isLocked,
)

fun List<NoteEntity>.toDomain(): List<Note> = map {it.toDomain()}

fun Note.toEntity(): NoteEntity = NoteEntity(
    title = title,
    path = path,
    createdAt = createdAt,
    updatedAt = updatedAt,
    syncedAt = syncedAt,
    isLocked = isLocked
)