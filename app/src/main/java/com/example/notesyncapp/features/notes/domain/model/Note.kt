package com.example.notesyncapp.features.notes.domain.model

data class Note (
    val title: String,
    val path: String,
    val content: String,
    val createdAt: Long,
    val updatedAt: Long,
    val syncedAt: Long,
    val isLocked: Boolean,
    val id: Int? = null,
)