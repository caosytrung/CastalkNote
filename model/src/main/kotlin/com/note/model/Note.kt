package com.note.model

import java.util.Date

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val lastModified: Date,
)