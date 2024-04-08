package com.lcbryant.alwaysontask.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.Calendar

class MyDayViewModel : ViewModel() {
    private val _calendar = Calendar.getInstance()

    var showBottomSheet by mutableStateOf(false)
        private set
    var showDatePicker by mutableStateOf(false)
        private set
    var showTimePicker by mutableStateOf(false)
        private set
    var selectedDate by mutableStateOf("")
        private set
    var selectedTime by mutableStateOf("")
        private set

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

    fun onTimeSelected(time: String) {
        selectedTime = time
    }
}
