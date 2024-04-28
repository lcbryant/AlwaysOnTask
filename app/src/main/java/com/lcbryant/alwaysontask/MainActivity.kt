package com.lcbryant.alwaysontask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lcbryant.alwaysontask.core.data.repository.TodoTaskRepository
import com.lcbryant.alwaysontask.core.database.LocalDatabase
import com.lcbryant.alwaysontask.core.ui.theme.AlwaysOnTaskTheme
import com.lcbryant.alwaysontask.feature.dailyplanner.DailyPlannerScreen
import com.lcbryant.alwaysontask.feature.dailyplanner.DailyPlannerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlwaysOnTaskTheme {
                val localDb = LocalDatabase.getDb(context = this)
                val viewModel = DailyPlannerViewModel(
                    todoTaskRepository = TodoTaskRepository(localDb.todoTaskDao())
                )
                DailyPlannerScreen(dailyPlannerViewModel = viewModel)
            }
        }
    }
}
