package com.lcbryant.alwaysontask.feature.myday

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

    val showBottomSheet = {
        scope.launch {
            sheetState.show()
        }.invokeOnCompletion { myDayViewModel.toggleAddEditTask() }
    }

    val hideBottomSheet = {
        scope.launch {
            sheetState.hide()
        }.invokeOnCompletion { myDayViewModel.toggleAddEditTask() }
    }

    val isBottomSheetVisible = {
        myDayViewModel.isAddEditTaskVisible() && sheetState.isVisible
    }

    val uiState by myDayViewModel.myDayUiState.collectAsState()

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
                    onClick = { myDayViewModel.onNukeTable() }
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
                onTodoTaskDelete = { myDayViewModel.onDeleteTask(it) },
                onTodoTaskEdit = { myDayViewModel.onEditTask(it) },
                onTodoTaskToggleComplete = { myDayViewModel.onTaskToggleComplete(it) },
            )
        }

        if (isBottomSheetVisible()) {
            TodoTaskCreation(
                onDismissRequest = { hideBottomSheet() },
                addTaskCallback = {
                    myDayViewModel.onAddTask()
                    hideBottomSheet()
                },
                toggleTimePicker = { myDayViewModel.toggleTimePicker() },
                toggleDatePicker = { myDayViewModel.toggleDatePicker() },
                onDateSelected = { myDayViewModel.onDateSelected(it) },
                onTimeSelected = { hour, min, is24hr ->
                    myDayViewModel.onTimeSelected(
                        hour,
                        min,
                        is24hr
                    )
                },
                onContentChanged = { myDayViewModel.onTaskContentChanged(it) },
                onDurationChanged = { myDayViewModel.onDurationChanged(it) },
                isDatePickerVisible = { myDayViewModel.isDatePickerVisible() },
                isTimePickerVisible = { myDayViewModel.isTimePickerVisible() },
                state = myDayViewModel.addEditTaskUiState,
                modifier = modifier,
                sheetState = sheetState,
            )
        }
    }
}
