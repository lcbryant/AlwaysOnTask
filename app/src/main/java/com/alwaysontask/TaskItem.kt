package com.alwaysontask

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun TaskItem(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {

}
