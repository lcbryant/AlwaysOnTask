package com.lcbryant.alwaysontask.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerComponent(
    modifier: Modifier = Modifier,
    calendar: Calendar = Calendar.getInstance(),
    yearRange: IntRange,
    formatDate: (Long) -> String,
    formatTime: (Int, Int, Boolean) -> String,
    is24hour: Boolean
) {
    val datePickerHidden = remember { mutableStateOf(false) }
    val timePickerHidden = remember { mutableStateOf(false) }

    val selectedDate = remember { mutableStateOf("") }
    val selectedTime = remember { mutableStateOf("") }

    val datePickerState = rememberDatePickerState(calendar.timeInMillis, yearRange = yearRange)
    val timePickerState = rememberTimePickerState(
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        is24hour
    )

    fun toggleDatePicker() {
        datePickerHidden.value = datePickerHidden.value.not()
    }

    fun toggleTimePicker() {
        timePickerHidden.value = timePickerHidden.value.not()
    }

    fun onDateSelected() {
        toggleDatePicker()
        selectedDate.value = datePickerState.selectedDateMillis?.let { formatDate(it) }.toString()
    }

    fun onTimeSelected() {
        toggleTimePicker()
        selectedTime.value = formatTime(
            timePickerState.hour,
            timePickerState.minute,
            timePickerState.is24hour
        )
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DatePickerButton(
                    modifier,
                    onClick = { toggleDatePicker() }
                )
            }

            Column(
                modifier = modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TimePickerButton(
                    modifier,
                    onClick = { toggleTimePicker() }
                )
            }
        }
    }


    if (datePickerHidden.value) {
        DatePickerDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                ConfirmButton(modifier, onClick = { onDateSelected() })
            },
            dismissButton = {
                DismissButton(
                    modifier,
                    onClick = { toggleDatePicker() }
                )
            }
        )
        {
            DatePicker(state = datePickerState)
        }
    }

    if (timePickerHidden.value) {
        TimePickerDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                ConfirmButton(modifier, onClick = { onTimeSelected() })
            },
            dismissButton = {
                DismissButton(
                    modifier,
                    onClick = { toggleTimePicker() }
                )
            }
        )
        {
            TimePicker(state = timePickerState)
        }
    }
}
