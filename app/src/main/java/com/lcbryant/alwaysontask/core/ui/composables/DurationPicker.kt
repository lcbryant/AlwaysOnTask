package com.lcbryant.alwaysontask.core.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lcbryant.alwaysontask.R
import com.lcbryant.alwaysontask.core.ui.theme.AlwaysOnTaskTheme
import java.time.Duration
import kotlin.math.absoluteValue

@Composable
fun DurationPicker(
    modifier: Modifier = Modifier,
    duration: Duration,
    onDurationChange: (Duration) -> Unit
) {
    var durationState by remember { mutableStateOf(duration) }
    var selectedDuration by remember { mutableStateOf(durationState.toMinutes().toString()) }

    fun onValueChange(value: String) {
        var localVar = value
        if (localVar.length <= DurationPickerDefaults.INPUT_LENGTH) {
            localVar = value.filter { it.isDigit() }
        }

        if (localVar.isEmpty()) {
            localVar = "0"
        }

        selectedDuration = localVar
        onDurationChange(Duration.ofMinutes(localVar.toLong()))
    }

    fun onIncreaseDecrease(minutes: Long) {
        durationState = durationState.plusMinutes(minutes)
        onValueChange(durationState.toMinutes().toString())
    }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { onIncreaseDecrease(-5) }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.rounded_dash_24),
                    contentDescription = "Decrease duration by 5 minute"
                )
                Text(text = "5")
            }
        }

        IconButton(
            onClick = { onIncreaseDecrease(-1) }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.rounded_dash_24),
                contentDescription = "Decrease duration by 1 minute"
            )
        }

        Column(
            modifier = Modifier
                .size(72.dp)
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DurationPickerTextField(
                modifier = Modifier.fillMaxSize(),
                text = selectedDuration,
                onValueChange = ::onValueChange
            )
        }

        IconButton(onClick = { onIncreaseDecrease(1) }) {
            Icon(
                painter = painterResource(id = R.drawable.rounded_add_24),
                contentDescription = "Increase duration by 1 minute"
            )
        }

        IconButton(
            onClick = { onIncreaseDecrease(5) }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.rounded_add_24),
                    contentDescription = "Increase duration by 5 minute"
                )
                Text(text = "5")
            }
        }
    }
}

@Composable
fun DurationPickerTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = MaskVisualTransformation(DurationPickerDefaults.MASK),
        textStyle = MaterialTheme.typography.bodyMedium
    )
}

@Preview
@Composable
fun DurationPickerPreview() {
    AlwaysOnTaskTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DurationPicker(
                duration = Duration.ofMinutes(45),
                onDurationChange = {}
            )
        }
    }

}

class MaskVisualTransformation(private val mask: String) : VisualTransformation {
    private val specialSymbolsIndices = mask.indices.filter { mask[it] != '#' }

    override fun filter(text: AnnotatedString): TransformedText {
        var out = ""
        var maskIndex = 0
        text.forEach { char ->
            while (specialSymbolsIndices.contains(maskIndex)) {
                out += mask[maskIndex]
                maskIndex++
            }
            out += char
            maskIndex++
        }
        return TransformedText(AnnotatedString(out), offsetTranslator())
    }

    private fun offsetTranslator() = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val offsetValue = offset.absoluteValue
            if (offsetValue == 0) return 0
            var numberOfHashtags = 0
            val masked = mask.takeWhile {
                if (it == '#') numberOfHashtags++
                numberOfHashtags < offsetValue
            }
            return masked.length + 1
        }

        override fun transformedToOriginal(offset: Int): Int {
            return mask.take(offset.absoluteValue).count { it == '#' }
        }
    }
}

object DurationPickerDefaults {
    const val MASK = "##"
    const val INPUT_LENGTH = 2
}
