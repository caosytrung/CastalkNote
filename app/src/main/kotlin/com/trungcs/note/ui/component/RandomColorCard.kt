package com.trungcs.note.ui.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.trungcs.note.util.OnButtonClick

@Composable
fun RandomColorCard(
    onCardClick: OnButtonClick,
    content: @Composable ColumnScope.() -> Unit,
) {
    val color = randomColors.random()
    Card(
        colors = CardDefaults.cardColors(containerColor = color),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.semantics {
            onCardClick()
        },
    ) {
        content()
    }
}

private val randomColors = listOf(
    Color(0xFFFCA591),
    Color(0xFFFFFFA1),
)