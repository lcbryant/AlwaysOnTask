package com.alwaysontask

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alwaysontask.ui.composables.DateTimePickerComponent
import com.alwaysontask.viewModels.MyDayViewModel

@Composable
fun MyDayScreen(
    modifier: Modifier = Modifier,
    myDayViewModel: MyDayViewModel = viewModel(),
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        DateTimePickerComponent(
            modifier = modifier,
            calendar = myDayViewModel.calendar,
            yearRange = 2020..2025,
            formatDate = myDayViewModel::formatDate,
            formatTime = myDayViewModel::formatTime,
            is24hour = false
        )
    }
}