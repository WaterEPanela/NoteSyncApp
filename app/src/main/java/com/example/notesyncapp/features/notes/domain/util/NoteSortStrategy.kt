package com.example.notesyncapp.features.notes.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}