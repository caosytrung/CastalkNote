package com.note.data.repo

import com.note.common.result.Result
import com.note.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    // Get and Observe changes of note table
    fun getAllTopicEntities(): Flow<Result<List<Note>>>

    // Upsert existing note, use id to classify that it's the insert of the update
    suspend fun upsertTopic(note: Note): Result<Unit>

    // Delete notes by ids
    suspend fun deleteNotes(ids: List<Int>): Result<Unit>

    // Delete all data
    suspend fun deleteAll(): Result<Unit>
}