package com.example.notesyncapp.features.notes.data.data_source

import android.R
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import javax.inject.Inject

class NoteFileStorage @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        const val NOTES_DIRECTORY= "notes"
    }

    private val notesDir: File by lazy {
        File(context.filesDir, NOTES_DIRECTORY).apply {
           if (!exists()) mkdirs()
        }
    }

    suspend fun writeNoteToFile(title: String, content: String): Result<File> = withContext(Dispatchers.IO) {
        try {
            val file = File(notesDir, "$title.md")
            file.writeText(content)
            Result.success(file)
        } catch (error: IOException) {
            Result.failure(error)
        }
    }

}