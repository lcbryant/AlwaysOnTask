package com.lcbryant.alwaysontask.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.lcbryant.alwaysontask.core.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun observeAll(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task WHERE id = :id")
    fun getById(id: Int): Flow<TaskEntity>

    @Query("SELECT * FROM task WHERE content LIKE :content")
    fun getByContent(content: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task WHERE scheduled_date = :scheduledDate")
    fun getByScheduledDate(scheduledDate: Long): Flow<List<TaskEntity>>

    @Insert
    suspend fun insert(taskEntity: TaskEntity)

    @Delete
    suspend fun delete(taskEntity: TaskEntity)

    @Update
    suspend fun update(taskEntity: TaskEntity)
}