package com.alwaysontask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alwaysontask.ui.theme.AlwaysOnTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlwaysOnTaskTheme {
                MyDayScreen()
            }
        }
    }
}
