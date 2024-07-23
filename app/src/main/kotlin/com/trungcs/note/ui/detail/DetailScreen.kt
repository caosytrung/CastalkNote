package com.trungcs.note.ui.detail

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.trungcs.note.util.OnButtonClick

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    onBackClick: OnButtonClick,
    viewModel: DetailViewModel = hiltViewModel()
) {
    Button(
        onClick = onBackClick,
        modifier = modifier.widthIn(min = 250.dp)
    ) {
        val noteId by viewModel.noteId.collectAsStateWithLifecycle()
        Text("$noteId")
    }
}