package com.lcbryant.alwaysontask.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lcbryant.alwaysontask.R

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
fun SaveButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_upward_24),
            contentDescription = "Save"
        )
    }
}

@Composable
fun StartTaskDurationButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    // TODO: add the 3 states for this button

    Button(
        onClick = onClick,
        modifier = modifier
            .heightIn(min = 20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.activity_duration_timer_toggle_button_start),
            fontSize = 8.sp,
        )
        Icon(
            painter = painterResource(R.drawable.twotone_play_arrow_24),
            contentDescription = "Start",
            modifier = modifier
                .padding(start = 8.dp)
                .size(10.dp)
        )
    }
}

@Preview
@Composable
fun ButtonsPreview() {
    StartTaskDurationButton(onClick = {})
}
