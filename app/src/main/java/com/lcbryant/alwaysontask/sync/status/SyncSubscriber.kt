package com.lcbryant.alwaysontask.sync.status

interface SyncSubscriber {
    suspend fun subscribe()
}