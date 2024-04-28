package com.lcbryant.alwaysontask.feature.dailyplanner

import com.lcbryant.alwaysontask.core.model.TodoTask
import kotlinx.coroutines.flow.Flow

data class DailyPlannerUiState(
    val todoTasks: Flow<List<TodoTask>>,
    val showAddEditTask: Boolean = false,
)
