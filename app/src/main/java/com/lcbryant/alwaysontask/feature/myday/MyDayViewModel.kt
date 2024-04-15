package com.lcbryant.alwaysontask.feature.myday

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcbryant.alwaysontask.core.data.repository.TodoTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    fun onTaskNotesChanged(note: String) {
        _addEditTaskUiState.update { it.copy(editNote = note) }
    }

    fun onAddTask() {
        val task = addEditTaskUiState.value.todoTask.copy(
            content = addEditTaskUiState.value.editContent,
            note = addEditTaskUiState.value.editNote,
            dueDate = addEditTaskUiState.value.dueDateInitial,
            dueTime = addEditTaskUiState.value.dueTimeInitial
        )

        viewModelScope.launch {
            todoTaskRepository.insertTask(task)
        }

        toggleAddEditTask()
    }


}