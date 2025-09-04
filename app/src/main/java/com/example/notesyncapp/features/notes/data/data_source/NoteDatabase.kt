package com.example.notesyncapp.features.notes.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesyncapp.features.notes.data.model.NoteEntity

@Database(
    version = 1,
    entities = [NoteEntity::class]
)
abstract class NoteDatabase:RoomDatabase() {
    abstract val noteDao: NoteDao
}