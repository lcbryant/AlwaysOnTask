package com.lcbryant.alwaysontask.core.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lcbryant.alwaysontask.R

@Composable
fun TodoTaskCreationButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable() (RowScope.() -> Unit)
) {
    Button(
        onClick,
        modifier,
        enabled,
        shape,
        colors,
        elevation,
        border,
        contentPadding,
        interactionSource,
        content,
    )
}

@Composable
fun TodoTaskCreationButtonContent(
    icon: Painter,
    iconDescription: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(text = text)
        Icon(painter = icon, contentDescription = iconDescription)
    }
}

@Composable
fun TodoTaskCreationButtonGroup(
    setTimeOnClick: () -> Unit,
    addDateOnClick: () -> Unit,
    // setRepeatOnClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val setTimeIcon = painterResource(R.drawable.round_access_time_24)
    val setTimeText = stringResource(R.string.todo_task_creation_set_time_button_text)
    val addDateIcon = painterResource(R.drawable.rounded_today_24)
    val addDateText = stringResource(R.string.todo_task_creation_add_date_button_text)
    // val setRepeatIcon = painterResource(R.drawable.rounded_repeat_24)
    // val setRepeatText = stringResource(R.string.todo_task_creation_set_repeat_button_text)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TodoTaskCreationOutlinedButton(
            onClick = setTimeOnClick,
            content = {
                TodoTaskCreationOutlinedButtonContent(
                    icon = setTimeIcon,
                    iconDescription = setTimeText,
                    text = setTimeText,
                )
            }
        )

        Spacer(modifier = Modifier.size(8.dp))

        TodoTaskCreationOutlinedButton(
            onClick = addDateOnClick,
            content = {
                TodoTaskCreationOutlinedButtonContent(
                    icon = addDateIcon,
                    iconDescription = addDateText,
                    text = addDateText,
                )
            }
        )

        /*Spacer(modifier = Modifier.size(4.dp))

        TodoTaskCreationOutlinedButton(
            onClick = setRepeatOnClick,
            content = {
                TodoTaskCreationOutlinedButtonContent(
                    icon = setRepeatIcon,
                    iconDescription = setRepeatText,
                    text = setRepeatText,
                )
            }
        )*/
    }
}

@Composable
fun TodoTaskCreationOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = MaterialTheme.shapes.small,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = ButtonDefaults.outlinedButtonBorder,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable() (RowScope.() -> Unit),
) {
    OutlinedButton(
        onClick,
        modifier,
        enabled,
        shape,
        colors,
        elevation,
        border,
        contentPadding,
        interactionSource,
        content,
    )
}

@Composable
fun TodoTaskCreationOutlinedButtonContent(
    icon: Painter,
    iconDescription: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(painter = icon, contentDescription = iconDescription)
        Text(text = text)
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
fun StartTaskDurationButton(
    modifier: Modifier = Modifier,
    fontSizeRange: FontSizeRange = FontSizeRange(2.sp, 16.sp),
    onClick: () -> Unit
) {
    // TODO: add the 3 states for this button

    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        AutoResizeText(
            text = stringResource(id = R.string.activity_duration_timer_toggle_button_start),
            fontSizeRange = fontSizeRange,
        )

        Icon(
            painter = painterResource(R.drawable.twotone_play_arrow_24),
            contentDescription = "Start",
        )
    }
}
