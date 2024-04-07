package com.lcbryant.alwaysontask.common

import java.util.Calendar

class TimeUtil {
    private val calendar = Calendar.getInstance()

    fun getCurrentDateTime(): String {
        return calendar.time.toString()
    }
}