package com.lcbryant.alwaysontask.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.lcbryant.alwaysontask.data.local.entity.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAll(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE id = :id")
    fun getById(id: Int): Flow<Task>

    @Query("SELECT * FROM task WHERE content LIKE :content")
    fun getByContent(content: String): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE scheduledDate = :scheduledDate")
    fun getByScheduledDate(scheduledDate: Long): Flow<List<Task>>

    @Insert
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Update
    suspend fun update(task: Task)
}