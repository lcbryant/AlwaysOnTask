package com.lcbryant.alwaysontask.core.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lcbryant.alwaysontask.R
import com.lcbryant.alwaysontask.feature.myday.AddEditTaskUiState
import kotlinx.coroutines.flow.StateFlow

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
    onNotesChanged: (String) -> Unit,
    isDatePickerVisible: () -> Boolean,
    isTimePickerVisible: () -> Boolean,
    sheetState: SheetState,
    state: StateFlow<AddEditTaskUiState>,
) {
    val datePickerState = rememberDatePickerState()
    val timePickerState = rememberTimePickerState()

    val addEditTaskUiState by state.collectAsStateWithLifecycle()

    TodoTaskCreationBottomSheet(
        onDismissRequest,
        addTaskCallback,
        toggleTimePicker,
        toggleDatePicker,
        onContentChanged,
        onNotesChanged,
        sheetState,
        addEditTaskUiState,
        modifier,
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
    onDismissRequest: () -> Unit,
    addTaskCallback: () -> Unit,
    toggleTimePicker: () -> Unit,
    toggleDatePicker: () -> Unit,
    onContentChanged: (String) -> Unit,
    onNotesChanged: (String) -> Unit,
    sheetState: SheetState,
    state: AddEditTaskUiState,
    modifier: Modifier = Modifier,
    sheetMaxWidth: Dp = BottomSheetDefaults.SheetMaxWidth,
    shape: Shape = BottomSheetDefaults.ExpandedShape,
    containerColor: Color = BottomSheetDefaults.ContainerColor,
    contentColor: Color = contentColorFor(containerColor),
    tonalElevation: Dp = BottomSheetDefaults.Elevation,
    scrimColor: Color = BottomSheetDefaults.ScrimColor,
    dragHandle: @Composable() (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
    windowInsets: WindowInsets = BottomSheetDefaults.windowInsets,
    properties: ModalBottomSheetProperties = ModalBottomSheetDefaults.properties(),
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetMaxWidth = sheetMaxWidth,
        shape = shape,
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
            onNotesChanged,
            state,
            modifier,
        )

        Spacer(modifier = Modifier.size(128.dp))
    }
}

@Composable
fun TodoTaskCreationContent(
    addTaskCallback: () -> Unit,
    toggleTimePicker: () -> Unit,
    toggleDatePicker: () -> Unit,
    onContentChanged: (String) -> Unit,
    onNotesChanged: (String) -> Unit,
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

        // Notes text field
        TodoTaskCreationTextField(
            value = state.editNote,
            onValueChange = onNotesChanged,
            placeholder = stringResource(R.string.todo_task_creation_notes_input_placeholder),
            modifier = Modifier.padding(vertical = 8.dp),
        )

        Spacer(modifier = Modifier.size(8.dp))

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
