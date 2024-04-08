package com.lcbryant.alwaysontask.sync.status

import android.content.Context
import androidx.work.WorkManager
import com.lcbryant.alwaysontask.core.data.common.SyncManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class WorkManagerSyncManager @Inject constructor(
    @ApplicationContext private val context: Context
) : SyncManager {
    override val isSyncing: Flow<Boolean> = TODO()

    override fun requestSync() {
        val workManager = WorkManager.getInstance(context)
        // Enqueue a new sync work request
    }
}