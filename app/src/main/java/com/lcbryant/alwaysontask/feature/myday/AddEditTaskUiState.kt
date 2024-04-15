package com.lcbryant.alwaysontask.feature.myday

import com.lcbryant.alwaysontask.common.DateTimeProviderImpl
import com.lcbryant.alwaysontask.core.model.TodoTask
import java.time.LocalDate
import java.time.LocalTime

data class AddEditTaskUiState(
    val todoTask: TodoTask = TodoTask(
        createdAt = DateTimeProviderImpl().now(),
        updatedAt = DateTimeProviderImpl().now(),
    ),
    val editContent: String = "",
    val editNote: String = "",
    val dueDateInitial: LocalDate = DateTimeProviderImpl().now().toLocalDate(),
    val dueTimeInitial: LocalTime = DateTimeProviderImpl().now().toLocalTime(),
    val showBottomSheet: Boolean = false,
    val showDatePicker: Boolean = false,
    val showTimePicker: Boolean = false,
    val isComplete: Boolean = false,
    val isSaved: Boolean = false,
    val scheduled: Boolean = false,
)
