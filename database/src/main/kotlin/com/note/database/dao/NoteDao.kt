package com.note.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.note.database.model.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    // Get and observe all notes from databases by using Flow.
    @Query(value = "SELECT * FROM notes")
    fun getAllTopicEntities(): Flow<List<NoteEntity>>

    // Inserts or updates [entities] in the db under the specified primary keys
    @Upsert
    suspend fun upsertTopic(entities: NoteEntity)

    // Deletes rows in the db matching the specified [ids]
    @Query(
        value = """
            DELETE FROM notes
            WHERE id in (:ids)
        """,
    )
    suspend fun deleteNotes(ids: List<Int>)

    // Deletes all data
    @Query(value = "DELETE FROM notes")
    suspend fun deleteAll()
}