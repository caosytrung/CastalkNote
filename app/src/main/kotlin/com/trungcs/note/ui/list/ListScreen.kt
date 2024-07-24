package com.trungcs.note.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.note.model.Note
import com.trungcs.myapplication.R
import com.trungcs.note.ui.component.LoadingWheel
import com.trungcs.note.ui.component.RandomColorCard
import com.trungcs.note.ui.detail.CREATE_NEW_NOTE_ID
import com.trungcs.note.ui.theme.NoteTheme
import com.trungcs.note.util.OnButtonClick

@Composable
fun ListNoteScreen(
    onSelectNoteClick: (Int) -> Unit,
    viewModel: ListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    Column {
        ListAppBar()

        Box(modifier = Modifier.fillMaxSize()) {
            when (uiState) {
                is ListUiState.Loading -> LoadingWheel(
                    "Loading",
                    modifier = Modifier.align(Alignment.Center)
                )

                is ListUiState.Error -> TODO()
                is ListUiState.Success -> ListNoteBody(
                    onSelectNoteClick = { onSelectNoteClick(it) },
                    onCreateNewNodeClick = { onSelectNoteClick(CREATE_NEW_NOTE_ID) },
                    (uiState as ListUiState.Success).listNotes
                )
            }
        }
    }
}

@Composable
fun ListAppBar() {
    Column(
        modifier = Modifier.padding(horizontal = 32.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = stringResource(id = R.string.list_note_title),
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
            )
        }

        Text(
            text = stringResource(id = R.string.app_name),
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge,
        )

    }
}

@Composable
fun ListNoteBody(
    onSelectNoteClick: (Int) -> Unit,
    onCreateNewNodeClick: OnButtonClick,
    notes: List<Note>,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalStaggeredGrid(
            modifier = Modifier.padding(32.dp),
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 16.dp,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(notes) {
                NoteItem(note = it) {
                    onSelectNoteClick.invoke(it)
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(64.dp),
            onClick = onCreateNewNodeClick,
        ) {
            Icon(Icons.Filled.Add, "Add", tint = Color.White)
        }
    }

}

@Composable
fun NoteItem(note: Note, onSelectNoteClick: (Int) -> Unit) {
    RandomColorCard(onCardClick = {
        onSelectNoteClick.invoke(note.id)
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 6,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black,
            )
        }
    }
}


@Preview
@Composable
fun AppBarPreview() {
    NoteTheme {
        Surface(
            color = Color.Black,
            modifier = Modifier.fillMaxSize()
        ) {
            ListAppBar()
        }
    }
}


@Preview
@Composable
fun ListScreenPreview() {
    NoteTheme {
        Surface(
            color = Color.Black,
            modifier = Modifier.fillMaxSize()
        ) {
            ListNoteBody(
                onSelectNoteClick = {}, onCreateNewNodeClick = {}, notes = listOf(
                    Note(
                        title = "Title 1",
                        content = "To set a fixed number of columns, you can use"
                    ),
                    Note(
                        title = "This is the new note",
                        content = "This divides the available width by the number of columns (or rows for a horizontal grid), and has each item take up that width (or height for a horizontal grid):"
                    ),
                    Note(
                        title = "Title 1",
                        content = "Please note that this padding is applied to the content, not to the LazyColumn itself. In the example above, the first item will add 8.dp padding to it’s top, the last item will add 8.dp to its bottom, and all items will have 16.dp padding on the left and the right."
                    ),
                    Note(
                        title = "Title 1",
                        content = "To set a fixed number of columns, you can use"
                    ),
                    Note(
                        title = "Title 1",
                        content = "To set a fixed number of columns, you can use"
                    ),
                    Note(
                        title = "Title 1",
                        content = "To set a fixed number of columns, you can use"
                    ),
                    Note(
                        title = "Title 1",
                        content = "To set a fixed number of columns, you can use"
                    ),
                    Note(
                        title = "Title 1",
                        content = "To set a fixed number of columns, you can use"
                    ),
                    Note(
                        title = "Title 1",
                        content = "To set a fixed number of columns, you can use"
                    ),
                    Note(
                        title = "Title 1",
                        content = "To set a fixed number of columns, you can use"
                    ),
                )
            )
        }

    }
}
