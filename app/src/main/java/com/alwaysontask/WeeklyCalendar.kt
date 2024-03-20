package com.alwaysontask

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.Date

@Composable
fun WeeklyCalendar(
    modifier: Modifier = Modifier,
    week: List<Date>,
    onSelected: (Date, Boolean) -> Unit,
) {
    LazyRow {
        items(
            items = week
        ) { date ->
            WeekDay(modifier, date)
        }
    }
}

@Composable
fun WeekDay(modifier: Modifier = Modifier, date: Date) {
    var day: String = date.toString()
    ElevatedCard(modifier = modifier.padding(8.dp)) {
        Text(text = date.toString())
    }
}
