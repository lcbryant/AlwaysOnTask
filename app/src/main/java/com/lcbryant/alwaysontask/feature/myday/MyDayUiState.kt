package com.lcbryant.alwaysontask.feature.myday

import com.lcbryant.alwaysontask.core.model.TodoTask
import kotlinx.coroutines.flow.Flow

data class MyDayUiState(
    val todoTasks: Flow<List<TodoTask>>,
    val showAddEditTask: Boolean = false,
)
