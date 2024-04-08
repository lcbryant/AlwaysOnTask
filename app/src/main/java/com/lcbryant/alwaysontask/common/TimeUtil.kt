package com.lcbryant.alwaysontask.common

import java.util.Calendar

class TimeUtil {
    private val calendar = Calendar.getInstance()

    fun getCurrentDateTime(): String {
        return calendar.time.toString()
    }

    companion object {
        fun formatDurationRangeTime(startTime: String, duration: Int): Pair<String, String> {
            val start = startTime.split(":")
            val startHour = start[0].toInt()
            val startMinute = start[1].toInt()
            val endHour = startHour + duration / 60
            val endMinute = startMinute + duration % 60

            val startTimeString = if (startMinute == 0) {
                "$startHour:00"
            } else if (startMinute < 10) {
                "$startHour:0$startMinute"
            } else {
                "$startHour:$startMinute"
            }

            val endTimeString = if (endMinute == 0) {
                "$endHour:00"
            } else if (endMinute < 10) {
                "$endHour:0$endMinute"
            } else {
                "$endHour:$endMinute"
            }

            return Pair(startTimeString, endTimeString)
        }

        fun formatDuration(duration: Int): String {
            val hours = duration / 60
            val minutes = duration % 60

            val hourString = when (hours) {
                0 -> ""
                1 -> "1 hr"
                else -> "$hours hrs"
            }

            val minuteString = when (minutes) {
                0 -> ""
                1 -> "1 min"
                else -> "$minutes mins"
            }

            return when {
                hourString.isNotEmpty() && minuteString.isNotEmpty() -> "$hourString $minuteString"
                hourString.isNotEmpty() -> hourString
                else -> minuteString
            }
        }
    }
}
