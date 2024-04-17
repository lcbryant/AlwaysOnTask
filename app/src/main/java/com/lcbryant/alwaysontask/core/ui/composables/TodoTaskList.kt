package com.lcbryant.alwaysontask.core.ui.composables

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lcbryant.alwaysontask.core.model.TodoTask
import kotlinx.coroutines.flow.Flow

@Composable
fun TodoTaskList(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical = if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    todoTaskStream: Flow<List<TodoTask>>,
    onTodoTaskEdit: (TodoTask) -> Unit,
    onTodoTaskDelete: (TodoTask) -> Unit,
    onTodoTaskToggleComplete: (TodoTask) -> Unit,
) {
    val todoTasks by todoTaskStream.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = modifier
            .padding(8.dp)
            .width(300.dp),
        state = state,
        contentPadding = contentPadding,
        reverseLayout = reverseLayout,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        flingBehavior = flingBehavior,
        userScrollEnabled = userScrollEnabled,
    ) {
        items(todoTasks, key = {t -> t.id}) { todoTask ->
            TodoTaskDisplayable(
                todoTask = todoTask,
                onEdit = { onTodoTaskEdit(todoTask) },
                onDelete = { onTodoTaskDelete(todoTask) },
                onToggleComplete = { onTodoTaskToggleComplete(todoTask) },
            )
        }
    }
}
