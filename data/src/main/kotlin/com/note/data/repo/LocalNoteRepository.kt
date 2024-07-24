package com.note.data.repo

import com.note.common.result.Result
import com.note.common.result.asResult
import com.note.common.result.result
import com.note.database.dao.NoteDao
import com.note.database.model.NoteEntity
import com.note.database.model.toModel
import com.note.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class LocalNoteRepository @Inject constructor(
    private val noteDao: NoteDao
) : NoteRepository {
    override suspend fun getNoteById(id: Int): Result<Note> = result {
        noteDao.getNoteEntity(id).toModel()
    }

    override fun getAllTopicEntities(): Flow<Result<List<Note>>> =
        noteDao.getAllTopicEntities()
            .map { it.map(NoteEntity::toModel) }
            .asResult()

    override suspend fun upsertTopic(note: Note) = result {
        noteDao.upsertTopic(note.toEntity())
    }

    override suspend fun deleteNotes(ids: List<Int>) = result {
        noteDao.deleteNotes(ids)
    }

    override suspend fun deleteAll() = result {
        noteDao.deleteAll()
    }
}

fun Note.toEntity() = NoteEntity(
    id = id,
    title = title,
    content = content,
    lastModified = lastModified,
)