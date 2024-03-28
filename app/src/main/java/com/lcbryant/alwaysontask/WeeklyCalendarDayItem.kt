package com.lcbryant.alwaysontask

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WeeklyCalendarDayItem(
    isToday: Boolean = false,
    isSelected: Boolean = false,
    dayOfWeek: String,
    dayOfMonth: Int,
) {
    ElevatedCard(modifier = Modifier.padding(8.dp)) {
        Text(text = dayOfWeek)
        Text(text = "$dayOfMonth")
    }
}