package com.lcbryant.alwaysontask.core.data

import androidx.annotation.WorkerThread
import com.lcbryant.alwaysontask.core.data.local.dao.TaskDao
import com.lcbryant.alwaysontask.core.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

class TodoTaskRepository(private val taskDao: TaskDao) {
    val allTasks: Flow<List<TaskEntity>> = taskDao.observeAll()

    @WorkerThread
    suspend fun insert(taskEntity: TaskEntity) {
        taskDao.insert(taskEntity)
    }
}