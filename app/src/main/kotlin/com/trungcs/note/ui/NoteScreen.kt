package com.trungcs.note.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.trungcs.note.ui.detail.route.detailScreen
import com.trungcs.note.ui.detail.route.navigateToDetail
import com.trungcs.note.ui.list.route.LIST_ROUTE
import com.trungcs.note.ui.list.route.listScreen
import com.trungcs.note.ui.theme.NoteTheme


@Composable
fun NoteApp(
    navController: NavHostController = rememberNavController()
) {
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = LIST_ROUTE,
            modifier = Modifier.padding(innerPadding)
        ) {
            listScreen { navController.navigateToDetail(it) }
            detailScreen { navController.popBackStack() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteTheme(darkTheme = true) {
        NoteApp()
    }
}
