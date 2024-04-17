package com.lcbryant.alwaysontask.core.ui.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.lcbryant.alwaysontask.core.model.TodoTaskProgressStatus
import kotlinx.coroutines.delay
import java.time.Duration
import kotlin.time.Duration.Companion.seconds

@Composable
fun TextMinuteSecondTimer(
    modifier: Modifier = Modifier,
    duration: Duration,
) {
    var timeLeft by remember { mutableLongStateOf(duration.seconds) }

    LaunchedEffect(key1 = timeLeft) {
        while (timeLeft > 0) {
            delay(1.seconds)
            timeLeft--
        }
    }

    val minString = if (timeLeft / 60 < 10) "0${timeLeft / 60}" else "${timeLeft / 60}"
    val secString = if (timeLeft % 60 < 10) "0${timeLeft % 60}" else "${timeLeft % 60}"

    Text(
        text = "$minString:$secString",
        modifier = modifier
    )
}

@Composable
fun AotVisualTimer(
    modifier: Modifier = Modifier,
    duration: Duration,
    progressStatus: TodoTaskProgressStatus
) {

}

