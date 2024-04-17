package com.lcbryant.alwaysontask.core.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lcbryant.alwaysontask.R
import com.lcbryant.alwaysontask.common.DateTimeProviderImpl
import com.lcbryant.alwaysontask.core.model.TodoTask
import com.lcbryant.alwaysontask.core.ui.theme.AlwaysOnTaskTheme
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun TodoTaskDisplayable(
    modifier: Modifier = Modifier,
    todoTask: TodoTask,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onToggleComplete: () -> Unit,
) {
    var isComplete by remember { mutableStateOf(todoTask.isComplete) }
    fun toggleComplete() {
        onToggleComplete()
        isComplete = !isComplete
    }

    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .then(modifier),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 12.dp,
        ),
    ) {
        TodoTaskCardContent(
            modifier = Modifier,
            todoTask,
            onEdit,
            onDelete,
            onToggleComplete = { toggleComplete() },
        )
    }

}

@Composable
fun TodoTaskCardContent(
    modifier: Modifier = Modifier,
    todoTask: TodoTask,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onToggleComplete: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(8.dp),
    ) {
        TodoTaskContentHeader(
            content = todoTask.content,
            dueDate = todoTask.dueDate,
            dueTime = todoTask.dueTime,
        )

        TodoTaskContentDuration(
            duration = todoTask.duration,
            onToggleComplete = onToggleComplete,
            isComplete = todoTask.isComplete,
        )

        TodoTaskContentFooter(
            onDelete = onDelete,
            onEdit = onEdit,
        )
    }
}


@Composable
fun TodoTaskContentHeader(
    modifier: Modifier = Modifier,
    content: String,
    dueDate: LocalDate?,
    dueTime: LocalTime?,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AutoResizeText(
                text = content,
                fontSizeRange = FontSizeRange(
                    MaterialTheme.typography.titleSmall.fontSize,
                    MaterialTheme.typography.titleMedium.fontSize
                ),
                fontWeight = FontWeight.SemiBold,
            )

            // due date + time
            DueDateTimeText(
                dueDate = dueDate,
                dueTime = dueTime,
                modifier = Modifier.height(16.dp),
            )
        }
    }
}

@Composable
fun TodoTaskContentDuration(
    modifier: Modifier = Modifier,
    duration: Duration,
    onToggleComplete: () -> Unit,
    isComplete: Boolean,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Duration: ${duration.toMinutes()} minutes",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.size(8.dp))

        // complete button
        IconButton(
            onClick = onToggleComplete,
            modifier = Modifier.size(16.dp),
        ) {
            val icon = if (isComplete) {
                painterResource(R.drawable.rounded_done_24)
            } else {
                painterResource(R.drawable.round_undo_24)
            }

            Icon(painter = icon, contentDescription = "Toggle complete")
        }
    }
}

@Composable
fun TodoTaskContentFooter(
    modifier: Modifier = Modifier,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
) {
    Row(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
        ) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // edit button
                IconButton(
                    onClick = onEdit,
                    modifier = Modifier.size(16.dp),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.round_edit_24),
                        contentDescription = "Edit task"
                    )
                }

                Spacer(modifier = Modifier.size(8.dp))

                // delete button
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier.size(16.dp),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.rounded_delete_24),
                        contentDescription = "Delete task"
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun TodoTaskDisplayablePreview() {
    AlwaysOnTaskTheme {
        TodoTaskDisplayable(
            todoTask = TodoTask(
                content = "Do the thing",
                duration = Duration.ofMinutes(10),
                dueDate = DateTimeProviderImpl().now().toLocalDate(),
                dueTime = DateTimeProviderImpl().now().toLocalTime(),
                createdAt = DateTimeProviderImpl().now(),
                updatedAt = DateTimeProviderImpl().now(),
            ),
            onEdit = {},
            onDelete = {},
            onToggleComplete = {},
        )
    }
}
