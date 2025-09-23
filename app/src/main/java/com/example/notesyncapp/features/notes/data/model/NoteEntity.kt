package com.example.notesyncapp.features.notes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    val title: String,
    val path: String,
    val createdAt: String,
    val updatedAt: String,
    val syncedAt: String,
    val isLocked: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
)
