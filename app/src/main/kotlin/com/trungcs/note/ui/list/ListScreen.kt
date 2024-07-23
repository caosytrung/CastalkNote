package com.trungcs.note.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.note.model.Note
import com.trungcs.note.ui.component.RandomColorCard
import com.trungcs.note.ui.theme.NoteTheme

@Composable
fun ListNoteScreen(
    onSelectNoteClick: (Int) -> Unit,
    uiState: ListUiState
) {
    ListNoteBody(onSelectNoteClick = {}, notes = (uiState as ListUiState.Success).listNotes)
}

@Composable
fun ListNoteBody(
    onSelectNoteClick: (Int) -> Unit,
    notes: List<Note>,
) {

    LazyVerticalStaggeredGrid(
        modifier = Modifier.padding(32.dp),
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 16.dp,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(notes) {
            NoteItem(note = it)
        }
    }
}

@Composable
fun NoteItem(note: Note, modifier: Modifier = Modifier) {
    RandomColorCard(onCardClick = {

    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 6,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun InterestsScreenEmpty() {
    NoteTheme {
        ListNoteBody(
            onSelectNoteClick = {}, notes = listOf(
                Note(title = "Title 1", content = "To set a fixed number of columns, you can use"),
                Note(
                    title = "This is the new note",
                    content = "This divides the available width by the number of columns (or rows for a horizontal grid), and has each item take up that width (or height for a horizontal grid):"
                ),
                Note(
                    title = "Title 1",
                    content = "Please note that this padding is applied to the content, not to the LazyColumn itself. In the example above, the first item will add 8.dp padding to it’s top, the last item will add 8.dp to its bottom, and all items will have 16.dp padding on the left and the right."
                ),
                Note(title = "Title 1", content = "To set a fixed number of columns, you can use"),
                Note(title = "Title 1", content = "To set a fixed number of columns, you can use"),
                Note(title = "Title 1", content = "To set a fixed number of columns, you can use"),
            )
        )
    }
}
