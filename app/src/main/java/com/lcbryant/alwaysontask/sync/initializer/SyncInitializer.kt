package com.lcbryant.alwaysontask.sync.initializer

import android.content.Context
import androidx.work.WorkManager

object Sync {
    fun initialize(context: Context) {
        WorkManager.getInstance(context).apply {

        }
    }
}