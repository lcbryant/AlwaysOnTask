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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lcbryant.alwaysontask.R
import com.lcbryant.alwaysontask.core.data.TodoTask

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoTaskCreationBottomSheet(
    onDismissRequest: () -> Unit,
    addTaskCallback: (TodoTask) -> Unit,
    hideSheetCallback: () -> Unit,
    toggleTimePicker: () -> Unit,
    toggleDatePicker: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState,
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
            hideSheetCallback,
            toggleTimePicker,
            toggleDatePicker,
        )

        Spacer(modifier = Modifier.size(128.dp))
    }
}

@Composable
fun TodoTaskCreationContent(
    addTaskCallback: (TodoTask) -> Unit,
    hideSheetCallback: () -> Unit,
    toggleTimePicker: () -> Unit,
    toggleDatePicker: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        TodoTaskActivityTextField(
            value = "",
            onValueChange = {},
            modifier = modifier,
        )

        Spacer(modifier = Modifier.size(8.dp))

        TodoTaskNotesTextField(
            value = "",
            onValueChange = {},
            modifier = modifier,
        )

        Spacer(modifier = Modifier.size(8.dp))

        TodoTaskCreationButtonGroup(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp),
            setTimeOnClick = toggleTimePicker,
            addDateOnClick = toggleDatePicker,
            // setRepeatOnClick = {}
        )

        Spacer(modifier = Modifier.size(8.dp))

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TodoTaskCreationButton(
                onClick = hideSheetCallback,
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
