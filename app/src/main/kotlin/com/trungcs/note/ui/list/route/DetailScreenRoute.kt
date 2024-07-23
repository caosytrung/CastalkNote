package com.trungcs.note.ui.list.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.trungcs.note.ui.list.ListNoteScreen

const val LIST_ROUTE = "list_route"

fun NavController.navigateToList(navOptions: NavOptions? = null) {
    navigate(LIST_ROUTE, navOptions)
}

fun NavGraphBuilder.listScreen(
    onSelectNoteClick: (Int) -> Unit,
) {
    composable(route = LIST_ROUTE) {
        ListNoteScreen(
            onSelectNoteClick = onSelectNoteClick
        )
    }
}
