package com.lcbryant.alwaysontask.core.ui.composables

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lcbryant.alwaysontask.R
import com.lcbryant.alwaysontask.common.DateTimeProviderImpl
import com.lcbryant.alwaysontask.core.model.TodoTask
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

enum class DragAnchors { Start, End }

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun TodoTaskDisplayable(
    modifier: Modifier = Modifier,
    todoTask: TodoTask,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    var isComplete by remember { mutableStateOf(todoTask.isComplete) }
    fun toggleComplete() {
        isComplete = !isComplete
    }

    val density = LocalDensity.current
    val defaultActionSize = 48.dp
    val actionSizePx = with(density) { defaultActionSize.toPx() }
    val anchors = remember {
        DraggableAnchors {
            DragAnchors.Start at 0f
            DragAnchors.End at -actionSizePx
        }
    }

    val anchoredDraggableState = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.Start,
            anchors = anchors,
            positionalThreshold = { with(density) { 56.dp.toPx() } },
            velocityThreshold = { with(density) { 100.dp.toPx() } },
            animationSpec = tween()
        )
    }

    SideEffect { anchoredDraggableState.updateAnchors(anchors) }

    Box(
        modifier = modifier
            .clip(RectangleShape)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        DragAction(
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = (anchoredDraggableState
                            .requireOffset() + actionSizePx)
                            .roundToInt(),
                        y = 0,
                    )
                },
            onClick = { /*TODO*/ },
            painter = painterResource(R.drawable.rounded_delete_24)
        )

        DragAction(
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = (anchoredDraggableState
                            .requireOffset())
                            .roundToInt(),
                        y = 0,
                    )
                },
            onClick = { /*TODO*/ },
            painter = painterResource(R.drawable.round_edit_24)
        )

        TodoTaskDraggable(
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = anchoredDraggableState
                            .requireOffset()
                            .roundToInt(),
                        y = 0,
                    )
                }
                .anchoredDraggable(
                    state = anchoredDraggableState,
                    orientation = Orientation.Horizontal,
                    reverseDirection = true,
                ),
            todoTask = todoTask,
            isComplete = isComplete,
            onCheckedChange = { toggleComplete() },
        )
    }
}

@Composable
fun TodoTaskDraggable(
    modifier: Modifier = Modifier,
    todoTask: TodoTask,
    isComplete: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart,
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .height(80.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            ElevatedCard(
                shape = MaterialTheme.shapes.medium,
                colors = CardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    disabledContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                ),
                modifier = Modifier
                    .fillMaxHeight()
                    .width(120.dp),
            ) {
                TodoTaskCardContent(todoTask = todoTask)
            }

            Column {
                Checkbox(
                    checked = isComplete,
                    onCheckedChange = onCheckedChange,
                )
            }
        }
    }
}

@Composable
fun TodoTaskCardContent(
    todoTask: TodoTask,
    modifier: Modifier = Modifier,
) {
    val dateFormatter = DateTimeFormatter.ofPattern("MM/dd")
    val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")

    Column(
        modifier = modifier.padding(8.dp),
    ) {
        // content
        Row {
            AutoResizeText(
                text = todoTask.content,
                fontSizeRange = FontSizeRange(6.sp, 8.sp),
                fontWeight = FontWeight.SemiBold,
            )
        }

        // note
        Row {
            if (todoTask.note.isNotEmpty()) {
                AutoResizeText(
                    text = todoTask.note,
                    fontSizeRange = FontSizeRange(4.sp, 6.sp),
                    fontWeight = FontWeight.Light,
                )
            }
        }

        // due date + time
        Row {
            if (todoTask.dueDate != null) {
                // extract MM/dd from due date
                var dueText = "Due ${todoTask.dueDate.format(dateFormatter)}"

                if (todoTask.dueTime != null) {
                    dueText += " @ ${todoTask.dueTime.format(timeFormatter)}"
                }

                AutoResizeText(
                    text = dueText,
                    fontSizeRange = FontSizeRange(4.sp, 6.sp),
                    fontWeight = FontWeight.Light,
                )
            }
        }
    }
}

@Composable
fun DragAction(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    painter: Painter,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                modifier = Modifier,
                painter = painter,
                contentDescription = null,
            )
        }
    }
}


@Preview
@Composable
fun TodoTaskDisplayablePreview() {
    TodoTaskDisplayable(
        todoTask = TodoTask(
            content = "Do the thing",
            note = "This is a note",
            dueDate = DateTimeProviderImpl().now().toLocalDate(),
            dueTime = DateTimeProviderImpl().now().toLocalTime(),
            createdAt = DateTimeProviderImpl().now(),
            updatedAt = DateTimeProviderImpl().now(),
        ),
        onEdit = {},
        onDelete = {},
    )
}
