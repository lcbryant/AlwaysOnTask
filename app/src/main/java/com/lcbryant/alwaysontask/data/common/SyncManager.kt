package com.lcbryant.alwaysontask.data.common

import kotlinx.coroutines.flow.Flow

// Reports on if syncing is in progress and allows requesting a sync
interface SyncManager {
    val isSyncing: Flow<Boolean>
    fun requestSync()
}