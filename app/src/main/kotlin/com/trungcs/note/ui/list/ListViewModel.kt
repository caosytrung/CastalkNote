package com.trungcs.note.ui.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.note.model.Note
import com.trungcs.note.ui.detail.route.NOTE_ID_ARG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val noteId: StateFlow<Int?> = savedStateHandle.getStateFlow(NOTE_ID_ARG, null)

}

sealed interface ListUiState {
    data class Success(val listNotes: List<Note>) : ListUiState
    data object Error : ListUiState
    data object Loading : ListUiState
}