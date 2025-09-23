package com.example.notesyncapp.di

import android.content.Context
import androidx.room.Room
import com.example.notesyncapp.features.notes.data.data_source.NoteDao
import com.example.notesyncapp.features.notes.data.data_source.NoteDatabase
import com.example.notesyncapp.features.notes.data.data_source.NoteFileStorage
import com.example.notesyncapp.features.notes.data.repository.NoteRepositoryImpl
import com.example.notesyncapp.features.notes.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent :: class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase, noteFileStorage: NoteFileStorage): NoteRepository {
        return NoteRepositoryImpl(db.noteDao, noteFileStorage)
    }
}