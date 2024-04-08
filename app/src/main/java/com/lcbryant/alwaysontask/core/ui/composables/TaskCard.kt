package com.lcbryant.alwaysontask.core.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lcbryant.alwaysontask.R
import com.lcbryant.alwaysontask.common.TimeUtil
import com.lcbryant.alwaysontask.common.TimeUtil.Companion.formatDuration
import com.lcbryant.alwaysontask.common.TimeUtil.Companion.formatDurationRangeTime
import com.lcbryant.alwaysontask.core.data.TodoTask


@Composable
fun DisplayableTask(
    todoTask: TodoTask,
    onTaskCheckedChange: (TodoTask) -> Unit,
    onDurationToggle: (TodoTask) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .size(width = 140.dp, height = 80.dp)
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TaskCard(
                todoTask = getTask(),
                onDurationToggle = {},
                modifier = modifier.padding(end = 4.dp)
            )

            Column(
                modifier = modifier.size(10.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TaskCheckBox(
                    todoTask = todoTask,
                    onTaskCheckedChange = onTaskCheckedChange,
                    modifier = modifier
                )
            }
        }

    }
}

@Composable
fun TaskCard(
    todoTask: TodoTask,
    onDurationToggle: (TodoTask) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = CardDefaults.elevatedShape,
    colors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ),
    elevation: CardElevation = CardDefaults.cardElevation(
        defaultElevation = 6.dp,
    ),
) {
    val durationRange = formatDurationRangeTime(todoTask.scheduledStartTime, todoTask.duration)

    ElevatedCard(
        modifier = modifier
            .size(width = 120.dp, height = 80.dp),
        shape = shape,
        colors = colors,
        elevation = elevation,
    ) {
        Column(
            modifier = modifier
                .size(width = 110.dp, height = 72.dp)
                .padding(start = 4.dp, top = 4.dp),
        ) {
            TodoTaskHeader(
                todoTask,
                onDurationToggle,
                modifier = modifier
                    .size(width = 100.dp, height = 16.dp)
            )

            TodoTaskDurationTimeRange(
                durationRange.first,
                durationRange.second,
                modifier
            )

            Row(
                modifier = modifier,
            ) {
                VerticalDivider(thickness = 1.dp)
                TodoTaskBody(todoTask.content, todoTask.notes, modifier)
            }
        }

    }
}


@Composable
fun TodoTaskHeader(
    todoTask: TodoTask,
    onDurationToggle: (TodoTask) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StartTaskDurationButton(
            onClick = { onDurationToggle(todoTask) },
        )

        VerticalDivider(thickness = 1.dp)

        Text(
            text = formatDuration(todoTask.duration),
            fontSize = 6.sp,
        )
    }
}

@Composable
fun TodoTaskDurationTimeRange(
    startTime: String,
    endTime: String,
    modifier: Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceAround,

    ) {
    Row(
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement,
    ) {
        Text(
            text = startTime,
            fontSize = 6.sp,
        )

        Icon(
            painter = painterResource(id = R.drawable.round_arrow_right_alt_24),
            contentDescription = "to",
            modifier = modifier.size(8.dp)
        )

        Text(
            text = endTime,
            fontSize = 6.sp,
        )
    }
}

@Composable
fun TodoTaskBody(
    content: String,
    notes: String,
    modifier: Modifier,
) {
    Row {
        Column(
            modifier = modifier
        ) {
            Row(
                modifier = modifier
            ) {
                Text(
                    text = content,
                    modifier = modifier,
                    fontSize = 12.sp
                )
            }

            Row(
                modifier = modifier
                    .padding(start = 4.dp)
            ) {
                Text(
                    text = notes,
                    fontSize = 8.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Light
                )
            }


        }
    }
}

@Composable
fun TaskCheckBox(
    todoTask: TodoTask,
    onTaskCheckedChange: (TodoTask) -> Unit,
    modifier: Modifier,
) {
    Checkbox(
        checked = false,
        onCheckedChange = { onTaskCheckedChange(todoTask) },
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun TaskCardPreview() {
    TaskCard(getTask(), {})
}

@Preview(showBackground = true)
@Composable
fun DisplayableTaskPreview() {
    DisplayableTask(getTask(), {}, {})
}

fun getTask(): TodoTask {
    val calendar = TimeUtil()

    return TodoTask(
        localId = 0,
        firebaseId = "",
        localUserId = 0,
        firebaseUserId = "",
        content = "Task",
        notes = "Notes",
        completed = false,
        inProgress = false,
        allDay = false,
        timeCreated = calendar.getCurrentDateTime(),
        duration = 30,
        durationPaused = false,
        scheduled = true,
        scheduledDate = "2021-10-10",
        scheduledStartTime = "12:00",
    )
}
