package com.example.notesyncapp.features.notes.domain.model

data class Note (
    val title: String,
    val path: String,
    val createdAt: String,
    val updatedAt: String,
    val syncedAt: String,
    val isLocked: Boolean,
    val id: Int? = null,
)