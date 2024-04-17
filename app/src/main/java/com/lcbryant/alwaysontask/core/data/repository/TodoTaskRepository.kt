package com.lcbryant.alwaysontask.core.data.repository

import com.lcbryant.alwaysontask.core.data.local.dao.TodoTaskDao
import com.lcbryant.alwaysontask.core.data.local.entity.TodoTaskEntity
import com.lcbryant.alwaysontask.core.extension.toEntity
import com.lcbryant.alwaysontask.core.extension.toModel
import com.lcbryant.alwaysontask.core.model.TodoTask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoTaskRepository @Inject constructor(private val todoTaskDao: TodoTaskDao) : TodoTaskRepo {
    private val allTasks: Flow<List<TodoTaskEntity>> = todoTaskDao.observeAll()

    override fun getAllTasksStream(): Flow<List<TodoTask>> =
        allTasks.map { it.map(TodoTaskEntity::toModel) }

    override fun getTaskByIdStream(taskId: Int): Flow<TodoTask> {
        return todoTaskDao.observeById(taskId).map { it.toModel() }
    }

    override suspend fun insertTask(todoTask: TodoTask) {
        todoTaskDao.insert(todoTask.toEntity())
    }

    override suspend fun updateTask(todoTask: TodoTask) {
        todoTaskDao.update(todoTask.toEntity())
    }

    override suspend fun deleteTask(todoTask: TodoTask) {
        todoTaskDao.delete(todoTask.toEntity())
    }

    suspend fun nukeTable() {
        todoTaskDao.nukeTable()
    }
}