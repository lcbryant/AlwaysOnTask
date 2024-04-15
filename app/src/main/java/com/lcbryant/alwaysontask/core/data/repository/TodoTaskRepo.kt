package com.lcbryant.alwaysontask.core.data.repository

import com.lcbryant.alwaysontask.core.model.TodoTask
import kotlinx.coroutines.flow.Flow

interface TodoTaskRepo {
    fun getAllTasksStream(): Flow<List<TodoTask>>

    fun getTaskByIdStream(taskId: Int): Flow<TodoTask>

    suspend fun insertTask(todoTask: TodoTask)

    suspend fun updateTask(todoTask: TodoTask)

    suspend fun deleteTask(todoTask: TodoTask)
}