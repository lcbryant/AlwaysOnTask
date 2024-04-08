package com.lcbryant.alwaysontask.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lcbryant.alwaysontask.core.data.TodoTaskRepository
import com.lcbryant.alwaysontask.core.data.local.entity.TaskEntity
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TodoTaskRepository) : ViewModel() {
    val allTasks: LiveData<List<TaskEntity>> = repository.allTasks.asLiveData()

    // Launching a new coroutine to insert the data in a non-blocking way
    fun insert(taskEntity: TaskEntity) = viewModelScope.launch {
        repository.insert(taskEntity)
    }
}

class TaskViewModelFactory(private val repository: TodoTaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}