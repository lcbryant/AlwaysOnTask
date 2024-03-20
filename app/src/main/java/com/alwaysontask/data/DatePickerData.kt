package com.alwaysontask.data

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

@OptIn(ExperimentalMaterial3Api::class)
data class DatePickerData(
    val datePickerVisible: MutableState<Boolean> = mutableStateOf(false),
    val selectedDate: MutableState<String> = mutableStateOf(""),
    val initialSelectedDateMillis: Long = System.currentTimeMillis(),

    )
