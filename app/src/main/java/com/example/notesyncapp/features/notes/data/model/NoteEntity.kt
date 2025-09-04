package com.example.notesyncapp.features.notes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    val title: String,
    val path: String,
    val createdAt: String,
    val updatedAt: String,
    val syncedAt: String,
    val isLocked: Boolean,
    @PrimaryKey val id: Int? = null,
)
