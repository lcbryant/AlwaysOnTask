package com.lcbryant.alwaysontask.viewModels

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MyDayViewModel(
    dateFormatter: SimpleDateFormat = SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.getDefault())
) : ViewModel() {
    private val _dateFormatter = dateFormatter

    private val _calendar = Calendar.getInstance()
    private val _today = _calendar.timeInMillis
    val dateFormatter: SimpleDateFormat get() = _dateFormatter
    val calendar: Calendar get() = _calendar

    fun getCurrentFormattedDate(): String {
        return _dateFormatter.format(_calendar.timeInMillis).dropLast(9)
    }

    fun formatDate(date: Long): String {
        return _dateFormatter.format(date).dropLast(9)
    }

    fun formatTime(hour: Int, minute: Int, is24hour: Boolean): String {
        return if (is24hour) {
            "$hour:$minute"
        } else {
            val h = if (hour > 12) hour - 12 else hour
            "$h:$minute ${if (hour < 12) "AM" else "PM"}"
        }
    }
}
