package com.lcbryant.alwaysontask

import androidx.annotation.WorkerThread
import com.lcbryant.alwaysontask.data.local.dao.TaskDao
import com.lcbryant.alwaysontask.data.local.entity.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: Flow<List<Task>> = taskDao.getAll()

    @WorkerThread
    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }
}