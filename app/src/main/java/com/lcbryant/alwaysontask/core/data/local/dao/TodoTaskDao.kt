package com.lcbryant.alwaysontask.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.lcbryant.alwaysontask.core.data.common.TODO_TASK_TABLE_NAME
import com.lcbryant.alwaysontask.core.data.local.entity.TodoTaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoTaskDao {
    @Query("SELECT * FROM $TODO_TASK_TABLE_NAME")
    fun observeAll(): Flow<List<TodoTaskEntity>>

    @Query("SELECT * FROM $TODO_TASK_TABLE_NAME WHERE id = :id")
    fun observeById(id: Int): Flow<TodoTaskEntity>

    @Insert
    suspend fun insert(todoTaskEntity: TodoTaskEntity)

    @Delete
    suspend fun delete(todoTaskEntity: TodoTaskEntity)

    @Update
    suspend fun update(todoTaskEntity: TodoTaskEntity)

    @Query("DELETE FROM $TODO_TASK_TABLE_NAME")
    suspend fun nukeTable()
}