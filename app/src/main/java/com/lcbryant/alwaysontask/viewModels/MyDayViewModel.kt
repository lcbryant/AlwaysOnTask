package com.lcbryant.alwaysontask.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.lcbryant.alwaysontask.core.data.TodoTask
import java.util.Calendar

class MyDayViewModel : ViewModel() {
    private val _calendar = Calendar.getInstance()

    var showBottomSheet by mutableStateOf(false)
        private set
    var showDatePicker by mutableStateOf(false)
        private set
    var showTimePicker by mutableStateOf(false)
        private set

    private var selectedDate by mutableStateOf("")
    private var selectedTime by mutableStateOf("")
    private var todoTaskContent by mutableStateOf("")
    private var todoTaskNotes by mutableStateOf("")

    fun toggleBottomSheet() {
        showBottomSheet = !showBottomSheet
    }

    fun toggleDatePicker() {
        showDatePicker = !showDatePicker
    }

    fun toggleTimePicker() {
        showTimePicker = !showTimePicker
    }

    fun onDateSelected(date: String) {
        selectedDate = date
    }

    fun onTimeSelected(hour: Int, min: Int, is24hr: Boolean) {
        selectedTime = "$hour:$min"
    }

    fun onTaskContentChanged(content: String) {
        todoTaskContent = content
    }

    fun onTaskNotesChanged(notes: String) {
        todoTaskNotes = notes
    }

    fun onAddTask() {
        // Add task to database
        val newTask = TodoTask(
            localId = 0,
            localUserId = 0,
            content = todoTaskContent,
            notes = todoTaskNotes,
            timeCreated = _calendar.time.toString(),
            duration = 0,
            scheduled = true,
            scheduledDate = selectedDate,
            scheduledStartTime = selectedTime
        )
    }
}
