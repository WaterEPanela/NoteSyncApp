package com.example.notesyncapp.features.notes.domain.util

import com.example.notesyncapp.features.notes.domain.model.Note

enum class SortOrder {
    ASCENDING,
    DESCENDING
}

interface NoteSortStrategy {
    fun sort(notes: List<Note>, order: SortOrder): List<Note>
}

class SortByTitle : NoteSortStrategy {
    override fun sort(notes: List<Note>, order: SortOrder): List<Note> {
        return when (order) {
            SortOrder.ASCENDING -> notes.sortedBy { it.title.lowercase() }
            SortOrder.DESCENDING -> notes.sortedByDescending { it.title.lowercase() }
        }
    }
}

class SortByDate : NoteSortStrategy {
    override fun sort(notes: List<Note>, order: SortOrder): List<Note> {
        return when (order) {
            SortOrder.ASCENDING -> notes.sortedBy { it.createdAt }
            SortOrder.DESCENDING -> notes.sortedByDescending { it.createdAt }
        }
    }
}