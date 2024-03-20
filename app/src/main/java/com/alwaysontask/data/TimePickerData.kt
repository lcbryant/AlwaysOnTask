package com.alwaysontask.data

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class TimePickerData @OptIn(ExperimentalMaterial3Api::class) constructor(
    val timePickerVisible: MutableState<Boolean> = mutableStateOf(false),
    val selectedTime: MutableState<String> = mutableStateOf(""),
    val timePickerState: TimePickerState
)
