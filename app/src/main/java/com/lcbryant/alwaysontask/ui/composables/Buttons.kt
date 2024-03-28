package com.lcbryant.alwaysontask.ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.alwaysontask.R

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