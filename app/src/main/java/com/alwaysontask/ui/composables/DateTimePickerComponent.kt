package com.alwaysontask.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alwaysontask.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerComponent(
    modifier: Modifier = Modifier,
    dateTimePickerViewModel: TimeDateViewModel = viewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "no date selected",
            modifier = modifier.padding(bottom = 8.dp)
        )

        DatePickerButton(
            modifier,
            onClick = { dateTimePickerViewModel.toggleDatePickerVisibility() })

        Divider(modifier = modifier.padding(vertical = 24.dp))

        Text(
            text = "no time selected",
            modifier = modifier.padding(bottom = 8.dp)
        )

        TimePickerButton(
            modifier,
            onClick = { dateTimePickerViewModel.toggleTimePickerVisibility() })
    }

    if (dateTimePickerViewModel.datePickerVisible) {
        DatePickerDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                ConfirmButton(
                    modifier,
                    onClick = { dateTimePickerViewModel.toggleDatePickerVisibility() }
                )
            },
            dismissButton = {
                DismissButton(
                    modifier,
                    onClick = { dateTimePickerViewModel.toggleDatePickerVisibility() }
                )
            }
        )
        {
            DatePicker(rememberDatePickerState())
        }
    }

    if (dateTimePickerViewModel.timePickerVisible) {
        TimePickerDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                ConfirmButton(
                    modifier,
                    onClick = { dateTimePickerViewModel.toggleTimePickerVisibility() }
                )
            },
            dismissButton = {
                DismissButton(
                    modifier,
                    onClick = { dateTimePickerViewModel.toggleTimePickerVisibility() }
                )
            }
        )
        {
            TimePicker(rememberTimePickerState())
        }
    }
}

@Composable
fun ConfirmButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.rounded_check_24),
            contentDescription = "Confirm"
        )
    }
}

@Composable
fun DismissButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.round_close_24),
            contentDescription = "Dismiss"
        )
    }
}

@Composable
fun DatePickerButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = modifier.size(24.dp)) {
        Icon(
            painter = painterResource(id = R.drawable.twotone_insert_invitation_24),
            contentDescription = "Date picker"
        )
    }
}

@Composable
fun TimePickerButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = modifier.size(24.dp)) {
        Icon(
            painter = painterResource(id = R.drawable.twotone_timer_24),
            contentDescription = "time picker"
        )
    }
}

@Composable
fun TimePickerDialog(
    modifier: Modifier = Modifier,
    title: String = "Select Time",
    onDismissRequest: () -> Unit,
    confirmButton: @Composable (() -> Unit),
    dismissButton: @Composable (() -> Unit),
    containerColor: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = containerColor
                ),
            color = containerColor
        ) {
            Column(
                modifier = modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = modifier.weight(1F))
                    dismissButton.invoke()
                    confirmButton()
                }
            }
        }
    }
}

data class TimeDatePicker(
    val timePickerVisible: MutableState<Boolean> = mutableStateOf(false),
    val datePickerVisible: MutableState<Boolean> = mutableStateOf(false)
)

class TimeDateViewModel : ViewModel() {
    private val _timeDatePicker = TimeDatePicker()
    val timePickerVisible: Boolean get() = _timeDatePicker.timePickerVisible.value
    val datePickerVisible: Boolean get() = _timeDatePicker.datePickerVisible.value

    fun toggleTimePickerVisibility() {
        _timeDatePicker.timePickerVisible.value = _timeDatePicker.timePickerVisible.value.not()
    }

    fun toggleDatePickerVisibility() {
        _timeDatePicker.datePickerVisible.value = _timeDatePicker.datePickerVisible.value.not()
    }
}
