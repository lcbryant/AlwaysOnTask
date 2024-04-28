package com.lcbryant.alwaysontask.feature.dailyplanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcbryant.alwaysontask.core.data.repository.TodoTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val todoTaskRepository: TodoTaskRepository,
) : ViewModel() {
    private val _addEditTaskUiState = MutableStateFlow(AddEditTaskUiState())
    val addEditTaskUiState: StateFlow<AddEditTaskUiState> = _addEditTaskUiState.asStateFlow()

    fun toggleBottomSheet() {
        _addEditTaskUiState.update { it.copy(showBottomSheet = !it.showBottomSheet) }
    }

    fun toggleDatePicker() {
        _addEditTaskUiState.update { it.copy(showDatePicker = !it.showDatePicker) }
    }

    fun toggleTimePicker() {
        _addEditTaskUiState.update { it.copy(showTimePicker = !it.showTimePicker) }
    }

    fun isBottomSheetVisible(): Boolean = addEditTaskUiState.value.showBottomSheet

    fun isDatePickerVisible(): Boolean =
        addEditTaskUiState.value.showDatePicker && isBottomSheetVisible()

    fun isTimePickerVisible(): Boolean =
        addEditTaskUiState.value.showTimePicker && isBottomSheetVisible()

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

    fun onAddTask() {
        val task = addEditTaskUiState.value.todoTask

        viewModelScope.launch {
            todoTaskRepository.insertTask(task)
        }
    }
}
