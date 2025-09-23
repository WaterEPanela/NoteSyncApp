package com.example.notesyncapp.features.notes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesyncapp.features.notes.domain.model.Note
import com.example.notesyncapp.features.notes.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {
    private val _saveState = MutableStateFlow<SaveState>(SaveState.Idle)
    val saveState: StateFlow<SaveState> = _saveState.asStateFlow()

    fun saveNote(note: Note) {
        viewModelScope.launch {
            _saveState.value = SaveState.Loading
            noteRepository.insertNote(note).fold(
                onSuccess = { note ->
                    _saveState.value = SaveState.Success(note)
                },
                onFailure = { error ->
                    _saveState.value = SaveState.Error(error.message ?: "Failed to save note")
                }
            )
        }
    }
}

sealed class SaveState {
    data object Idle : SaveState()
    data object Loading : SaveState()
    data class Success(val note: Note) : SaveState()
    data class Error(val message: String) : SaveState()
}