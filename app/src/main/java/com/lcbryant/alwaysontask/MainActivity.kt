package com.lcbryant.alwaysontask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lcbryant.alwaysontask.core.ui.theme.AlwaysOnTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlwaysOnTaskTheme {
                MyDayScreen(
                    addTaskCallback = {}
                )
            }
        }
    }
}
