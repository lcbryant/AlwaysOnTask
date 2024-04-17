package com.lcbryant.alwaysontask.feature.myday

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcbryant.alwaysontask.core.data.repository.TodoTaskRepository
import com.lcbryant.alwaysontask.core.model.TodoTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class MyDayViewModel @Inject constructor(
    private val todoTaskRepository: TodoTaskRepository,
) : ViewModel() {
    private val _myDayUiState = MutableStateFlow(MyDayUiState(todoTaskRepository.getAllTasksStream()))
    val myDayUiState = _myDayUiState.asStateFlow()

    private val _addEditTaskUiState = MutableStateFlow(AddEditTaskUiState())
    val addEditTaskUiState = _addEditTaskUiState.asStateFlow()

    private fun clearAddEditTaskUiState() {
        _addEditTaskUiState.update { AddEditTaskUiState() }
    }

    fun toggleAddEditTask() {
        _myDayUiState.update { it.copy(showAddEditTask = !it.showAddEditTask) }
    }

    fun toggleDatePicker() {
        _addEditTaskUiState.update { it.copy(showDatePicker = !it.showDatePicker) }
    }

    fun toggleTimePicker() {
        _addEditTaskUiState.update { it.copy(showTimePicker = !it.showTimePicker) }
    }

    fun isAddEditTaskVisible(): Boolean = myDayUiState.value.showAddEditTask

    fun isDatePickerVisible(): Boolean =
        addEditTaskUiState.value.showDatePicker && isAddEditTaskVisible()

    fun isTimePickerVisible(): Boolean =
        addEditTaskUiState.value.showTimePicker && isAddEditTaskVisible()

    fun onDateSelected(dateInMillis: Long) {
        val localDate = Instant.ofEpochMilli(dateInMillis)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
        _addEditTaskUiState.update { it.copy(dueDateInitial = localDate) }
        toggleDatePicker()
    }

    fun onTimeSelected(hour: Int, min: Int, is24hr: Boolean) {
        val localTime = LocalTime.of(hour, min)
        _addEditTaskUiState.update { it.copy(dueTimeInitial = localTime) }
        toggleTimePicker()
    }

    fun onTaskContentChanged(content: String) {
        _addEditTaskUiState.update { it.copy(editContent = content) }
    }

    fun onDurationChanged(duration: Duration) {
        _addEditTaskUiState.update { it.copy(editDuration = duration) }
    }
    fun onAddTask() {
        // TODO: add validation
        // TODO: figure out duration input

        val task = addEditTaskUiState.value.todoTask.copy(
            content = addEditTaskUiState.value.editContent,
            duration = addEditTaskUiState.value.editDuration,
            dueDate = addEditTaskUiState.value.dueDateInitial,
            dueTime = addEditTaskUiState.value.dueTimeInitial
        )

        viewModelScope.launch {
            todoTaskRepository.insertTask(task)
        }

        clearAddEditTaskUiState()
        toggleAddEditTask()
    }

    fun onDeleteTask(task: TodoTask) {
        viewModelScope.launch {
            todoTaskRepository.deleteTask(task)
        }
    }

    fun onEditTask(task: TodoTask) {
        _addEditTaskUiState.update {
            it.copy(
                editContent = task.content,
                editDuration = task.duration,
                dueDateInitial = task.dueDate,
                dueTimeInitial = task.dueTime,
                todoTask = task
            )
        }

        toggleAddEditTask()
    }

    fun onTaskToggleComplete(task: TodoTask) {
        viewModelScope.launch {
            todoTaskRepository.updateTask(task.copy(isComplete = true))
        }
    }

    fun onNukeTable() {
        viewModelScope.launch {
            todoTaskRepository.nukeTable()
        }
    }
}