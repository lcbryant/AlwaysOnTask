package com.lcbryant.alwaysontask.app

import android.app.Application
import com.lcbryant.alwaysontask.sync.initializer.Sync
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AotApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Sync; this is a placeholder for the actual implementation
        Sync.initialize(context = this)
    }
}