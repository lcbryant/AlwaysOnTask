package com.lcbryant.alwaysontask.core.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.TimePicker
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lcbryant.alwaysontask.R
import com.lcbryant.alwaysontask.feature.dailyplanner.AddEditTaskUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.Duration

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TodoTaskCreation(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    addTaskCallback: () -> Unit,
    toggleTimePicker: () -> Unit,
    toggleDatePicker: () -> Unit,
    onDateSelected: (Long) -> Unit,
    onTimeSelected: (Int, Int, Boolean) -> Unit,
    onContentChanged: (String) -> Unit,
    onDurationChanged: (Duration) -> Unit,
    isDatePickerVisible: () -> Boolean,
    isTimePickerVisible: () -> Boolean,
    sheetState: SheetState,
    state: StateFlow<AddEditTaskUiState>,
) {
    val datePickerState = rememberDatePickerState()
    val timePickerState = rememberTimePickerState()

    val addEditTaskUiState by state.collectAsStateWithLifecycle()

    TodoTaskCreationBottomSheet(
        addTaskCallback,
        toggleTimePicker,
        toggleDatePicker,
        onContentChanged,
        onDurationChanged,
        addEditTaskUiState,
        onDismissRequest,
        modifier,
        sheetState,
    )

    if (isDatePickerVisible()) {
        DatePickerDialog(
            onDismissRequest = toggleDatePicker,
            confirmButton = {
                ConfirmButton(
                    modifier = Modifier,
                    onClick = {
                        onDateSelected(datePickerState.selectedDateMillis ?: 0L)
                    }
                )
            },
            dismissButton = {
                DismissButton(
                    modifier = Modifier,
                    onClick = toggleDatePicker
                )
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (isTimePickerVisible()) {
        TimePickerDialog(
            modifier = Modifier,
            onDismissRequest = toggleTimePicker,
            confirmButton = {
                ConfirmButton(
                    modifier = Modifier,
                    onClick = {
                        onTimeSelected(
                            timePickerState.hour,
                            timePickerState.minute,
                            timePickerState.is24hour
                        )
                    }
                )
            },
            dismissButton = {
                DismissButton(
                    modifier = Modifier,
                    onClick = toggleTimePicker
                )
            },
        ) {
            TimePicker(state = timePickerState)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TodoTaskCreationBottomSheet(
    addTaskCallback: () -> Unit,
    toggleTimePicker: () -> Unit,
    toggleDatePicker: () -> Unit,
    onContentChanged: (String) -> Unit,
    onDurationChanged: (Duration) -> Unit,
    state: AddEditTaskUiState,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    sheetMaxWidth: Dp = BottomSheetDefaults.SheetMaxWidth,
    shape: Shape = BottomSheetDefaults.ExpandedShape,
    containerColor: Color = BottomSheetDefaults.ContainerColor,
    contentColor: Color = contentColorFor(containerColor),
    tonalElevation: Dp = BottomSheetDefaults.Elevation,
    scrimColor: Color = BottomSheetDefaults.ScrimColor,
    dragHandle: @Composable() (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
    windowInsets: WindowInsets = WindowInsets.ime,
    properties: ModalBottomSheetProperties = ModalBottomSheetDefaults.properties(),
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = Modifier
            .imePadding()
            .consumeWindowInsets(windowInsets)
            .then(modifier),
        sheetState = sheetState,
        sheetMaxWidth = sheetMaxWidth,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        scrimColor = scrimColor,
        dragHandle = dragHandle,
        windowInsets = windowInsets,
        properties = properties,
    ) {
        TodoTaskCreationContent(
            addTaskCallback,
            toggleTimePicker,
            toggleDatePicker,
            onContentChanged,
            onDurationChanged,
            state,
        )
    }
}

@Composable
fun TodoTaskCreationContent(
    addTaskCallback: () -> Unit,
    toggleTimePicker: () -> Unit,
    toggleDatePicker: () -> Unit,
    onContentChanged: (String) -> Unit,
    onDurationChanged: (Duration) -> Unit,
    state: AddEditTaskUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Content text field
        TodoTaskCreationTextField(
            value = state.editContent,
            onValueChange = onContentChanged,
            placeholder = stringResource(R.string.todo_task_creation_task_input_placeholder),
            modifier = Modifier.padding(vertical = 8.dp),
        )

        Spacer(modifier = Modifier.size(8.dp))

        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DurationPicker(
                duration = state.editDuration,
                onDurationChange = { onDurationChanged(it) },
            )
        }

        TodoTaskCreationButtonGroup(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            setTimeOnClick = toggleTimePicker,
            addDateOnClick = toggleDatePicker,
        )

        Spacer(modifier = Modifier.size(8.dp))

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TodoTaskCreationButton(
                onClick = { addTaskCallback() },
                modifier = modifier,
                content = {
                    TodoTaskCreationButtonContent(
                        icon = painterResource(R.drawable.rounded_arrow_upward_24),
                        iconDescription = stringResource(R.string.activity_add),
                        text = stringResource(R.string.activity_add),
                    )
                }
            )
        }
    }
}

@Composable
fun TodoTaskCreationSelectedDateTimeDisplay(
    modifier: Modifier = Modifier,
    date: String,
    time: String,
) {

}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TodoTaskCreationPreview() {
    val sheetState = rememberModalBottomSheetState()
    val state = MutableStateFlow(AddEditTaskUiState())
    val scope = rememberCoroutineScope()

    scope.launch { sheetState.show() }

    if (sheetState.isVisible) {
        TodoTaskCreation(
            onDismissRequest = {},
            addTaskCallback = {},
            toggleTimePicker = {},
            toggleDatePicker = {},
            onDateSelected = {},
            onTimeSelected = { _, _, _ -> },
            onContentChanged = {},
            onDurationChanged = {},
            isDatePickerVisible = { false },
            isTimePickerVisible = { false },
            sheetState = sheetState,
            state = state,
        )
    }
}
