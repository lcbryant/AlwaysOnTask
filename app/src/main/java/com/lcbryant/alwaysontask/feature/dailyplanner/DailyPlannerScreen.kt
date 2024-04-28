package com.lcbryant.alwaysontask.feature.dailyplanner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lcbryant.alwaysontask.R
import com.lcbryant.alwaysontask.core.ui.composables.TodoTaskCreation
import com.lcbryant.alwaysontask.core.ui.composables.TodoTaskList
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DailyPlannerScreen(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
    dailyPlannerViewModel: DailyPlannerViewModel = viewModel(),
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val showBottomSheet = {
        scope.launch {
            sheetState.show()
        }.invokeOnCompletion { dailyPlannerViewModel.toggleAddEditTask() }
    }

    val hideBottomSheet = {
        scope.launch {
            sheetState.hide()
        }.invokeOnCompletion { dailyPlannerViewModel.toggleAddEditTask() }
    }

    val isBottomSheetVisible = {
        dailyPlannerViewModel.isAddEditTaskVisible() && sheetState.isVisible
    }

    val uiState by dailyPlannerViewModel.myDayUiState.collectAsState()

    val lazyListState = rememberLazyListState()

    Scaffold(
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = {
            FloatingActionButton(
                content = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = { showBottomSheet() }
            )
        },
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { dailyPlannerViewModel.onNukeTable() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.rounded_delete_24),
                        contentDescription = "delete all tasks"
                    )
                }
            }

            TodoTaskList(
                modifier = Modifier.padding(8.dp),
                state = lazyListState,
                contentPadding = innerPadding,
                todoTaskStream = uiState.todoTasks,
                onTodoTaskDelete = { dailyPlannerViewModel.onDeleteTask(it) },
                onTodoTaskEdit = { dailyPlannerViewModel.onEditTask(it) },
                onTodoTaskToggleComplete = { dailyPlannerViewModel.onTaskToggleComplete(it) },
            )
        }

        if (isBottomSheetVisible()) {
            TodoTaskCreation(
                onDismissRequest = { hideBottomSheet() },
                addTaskCallback = {
                    dailyPlannerViewModel.onAddTask()
                    hideBottomSheet()
                },
                toggleTimePicker = { dailyPlannerViewModel.toggleTimePicker() },
                toggleDatePicker = { dailyPlannerViewModel.toggleDatePicker() },
                onDateSelected = { dailyPlannerViewModel.onDateSelected(it) },
                onTimeSelected = { hour, min, is24hr ->
                    dailyPlannerViewModel.onTimeSelected(
                        hour,
                        min,
                        is24hr
                    )
                },
                onContentChanged = { dailyPlannerViewModel.onTaskContentChanged(it) },
                onDurationChanged = { dailyPlannerViewModel.onDurationChanged(it) },
                isDatePickerVisible = { dailyPlannerViewModel.isDatePickerVisible() },
                isTimePickerVisible = { dailyPlannerViewModel.isTimePickerVisible() },
                state = dailyPlannerViewModel.addEditTaskUiState,
                modifier = modifier,
                sheetState = sheetState,
            )
        }
    }
}
