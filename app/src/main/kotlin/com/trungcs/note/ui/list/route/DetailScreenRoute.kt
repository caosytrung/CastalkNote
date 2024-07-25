package com.trungcs.note.ui.list.route

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.trungcs.note.ui.list.ListNoteScreen

const val LIST_ROUTE = "list_route"

fun NavGraphBuilder.listScreen(
    onSelectNoteClick: (Int) -> Unit,
) {
    composable(route = LIST_ROUTE) {
        ListNoteScreen(onSelectNoteClick = onSelectNoteClick)
    }
}
