package com.trungcs.note

import androidx.lifecycle.SavedStateHandle
import com.note.common.result.Result
import com.trungcs.note.repo.MockNoteRepository
import com.trungcs.note.ui.detail.CREATE_NEW_NOTE_ID
import com.trungcs.note.ui.detail.DetailUiState
import com.trungcs.note.ui.detail.DetailViewModel
import com.trungcs.note.ui.detail.route.NOTE_ID_ARG
import com.trungcs.note.util.MainDispatcherRule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class DetailViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val noteIdStateFlow = MutableStateFlow(1)
    private val savedStateHandle: SavedStateHandle = mock()
    private val successMockRepository = MockNoteRepository()
    private lateinit var viewModel: DetailViewModel

    @Before
    fun setup() {
        `when`(savedStateHandle.getStateFlow(NOTE_ID_ARG, CREATE_NEW_NOTE_ID)).thenReturn(
            noteIdStateFlow
        )
    }

    @Test
    fun DetailViewModel_test_init_state() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel = DetailViewModel(savedStateHandle, successMockRepository)
            assert(viewModel.uiStateFlow.value is DetailUiState.Loading)
        }
        collectJob.cancel()
    }

    @Test
    fun DetailViewModel_test_get_note_by_id_success() = runTest {
        successMockRepository.upsertNote(noteFactory(1))
        viewModel = DetailViewModel(savedStateHandle, successMockRepository)
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.uiStateFlow.collect()
        }

        val curState = viewModel.uiStateFlow.value
        assert(curState is DetailUiState.Success)

        collectJob.cancel()
    }

    @Test
    fun DetailViewModel_test_get_note_by_id_failed() = runTest {
        viewModel = DetailViewModel(savedStateHandle, successMockRepository)
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.uiStateFlow.collect()
        }

        val curState = viewModel.uiStateFlow.value
        assert(curState is DetailUiState.Error)

        collectJob.cancel()
    }

    @Test
    fun DetailViewModel_test_update_title() = runTest {
        successMockRepository.upsertNote(noteFactory(1))
        viewModel = DetailViewModel(savedStateHandle, successMockRepository)
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.uiStateFlow.collect()
        }

        var curState = viewModel.uiStateFlow.value
        assert(curState is DetailUiState.Success)

        viewModel.onTitleChange("updated title")
        curState = viewModel.uiStateFlow.value
        assert(curState is DetailUiState.Success)
        assertEquals((curState as DetailUiState.Success).newTitle, "updated title")

        collectJob.cancel()
    }

    @Test
    fun DetailViewModel_test_update_content() = runTest {
        successMockRepository.upsertNote(noteFactory(1))
        viewModel = DetailViewModel(savedStateHandle, successMockRepository)
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.uiStateFlow.collect()
        }

        viewModel.onContentChange("updated content")
        val curState = viewModel.uiStateFlow.value
        assert(curState is DetailUiState.Success)
        assertEquals((curState as DetailUiState.Success).newContent, "updated content")

        collectJob.cancel()
    }

    @Test
    fun DetailViewModel_test_onSave_raise_empty_title_error_() = runTest {
        successMockRepository.upsertNote(noteFactory(1))
        viewModel = DetailViewModel(savedStateHandle, successMockRepository)
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.uiStateFlow.collect()
        }

        viewModel.onTitleChange("")
        viewModel.onSave()
        val curState = viewModel.uiStateFlow.value

        assertEquals((curState as DetailUiState.Success).isEmptyTitleError, true)

        collectJob.cancel()
    }

    @Test
    fun DetailViewModel_test_onSave_update_note() = runTest {
        successMockRepository.upsertNote(noteFactory(1))
        viewModel = DetailViewModel(savedStateHandle, successMockRepository)
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.uiStateFlow.collect()
        }

        viewModel.onTitleChange("updated note")
        viewModel.onSave()
        val listNotes = successMockRepository.getNoteById(1)
        assert(listNotes is Result.Success)
        assertEquals((listNotes as Result.Success).data.title, "updated note")

        collectJob.cancel()
    }

    @Test
    fun DetailViewModel_test_clear_empty_error_title() = runTest {
        successMockRepository.upsertNote(noteFactory(1))
        viewModel = DetailViewModel(savedStateHandle, successMockRepository)
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.uiStateFlow.collect()
        }

        viewModel.onTitleChange("")
        viewModel.onSave()
        var curState = viewModel.uiStateFlow.value

        assertEquals((curState as DetailUiState.Success).isEmptyTitleError, true)
        viewModel.clearEmptyTitleError()

        curState = viewModel.uiStateFlow.value
        assertEquals((curState as DetailUiState.Success).isEmptyTitleError, false)

        collectJob.cancel()
    }

    @Test
    fun DetailViewModel_test_ui_state_saveable() = runTest {
        val state = DetailUiState.Success(
            note = noteFactory(1),
            newTitle = "new title"
        )

        assert(state.isSaveAble())
    }

    @Test
    fun DetailViewModel_test_ui_state_note_saveable() = runTest {
        var state: DetailUiState = DetailUiState.Loading
        assertFalse(state.isSaveAble())

        state = DetailUiState.Error
        assertFalse(state.isSaveAble())

        state = DetailUiState.Success(
            note = noteFactory(1, "title"),
            newTitle = "title"
        )
        assertFalse(state.isSaveAble())
    }

}