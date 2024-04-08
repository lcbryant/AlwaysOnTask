package com.lcbryant.alwaysontask

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lcbryant.alwaysontask.core.ui.composables.ConfirmButton
import com.lcbryant.alwaysontask.core.ui.composables.DismissButton
import com.lcbryant.alwaysontask.core.ui.composables.TimePickerDialog
import com.lcbryant.alwaysontask.core.ui.composables.TodoTaskCreationBottomSheet
import com.lcbryant.alwaysontask.viewModels.MyDayViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDayScreen(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    myDayViewModel: MyDayViewModel = viewModel(),
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val datePickerState = rememberDatePickerState()
    val timePickerState = rememberTimePickerState()

    val hideSheetCallback = {
        scope.launch { sheetState.hide() }.invokeOnCompletion {
            if (!sheetState.isVisible) {
                myDayViewModel.toggleBottomSheet()
            }
        }
        Unit
    }

    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = {
            FloatingActionButton(
                content = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    myDayViewModel.toggleBottomSheet()
                }
            )
        },
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            // content
        }

        if (myDayViewModel.showBottomSheet) {
            TodoTaskCreationBottomSheet(
                onDismissRequest = {
                    hideSheetCallback()
                },
                addTaskCallback = {
                    myDayViewModel.onAddTask()
                    hideSheetCallback()
                },
                hideSheetCallback = hideSheetCallback,
                toggleTimePicker = { myDayViewModel.toggleTimePicker() },
                toggleDatePicker = { myDayViewModel.toggleDatePicker() },
                modifier = modifier,
                sheetState = sheetState,
            )
        }

        if (myDayViewModel.showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { myDayViewModel.toggleDatePicker() },
                confirmButton = {
                    ConfirmButton(
                        modifier = Modifier,
                        onClick = {}
                    )
                },
                dismissButton = {
                    DismissButton(
                        modifier = Modifier,
                        onClick = { myDayViewModel.toggleDatePicker() }
                    )
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        if (myDayViewModel.showTimePicker) {
            TimePickerDialog(
                modifier = Modifier,
                title = "Select Time",
                onDismissRequest = { myDayViewModel.toggleTimePicker() },
                confirmButton = {
                    ConfirmButton(
                        modifier = Modifier,
                        onClick = {}
                    )
                },
                dismissButton = {
                    DismissButton(
                        modifier = Modifier,
                        onClick = { myDayViewModel.toggleTimePicker() }
                    )
                },
            ) {
                TimePicker(state = timePickerState)
            }
        }
    }
}
