package com.lcbryant.alwaysontask.data

import androidx.annotation.WorkerThread
import com.lcbryant.alwaysontask.data.local.dao.TaskDao
import com.lcbryant.alwaysontask.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

class TodoTaskRepository(private val taskDao: TaskDao) {
    val allTasks: Flow<List<TaskEntity>> = taskDao.observeAll()

    @WorkerThread
    suspend fun insert(taskEntity: TaskEntity) {
        taskDao.insert(taskEntity)
    }
}