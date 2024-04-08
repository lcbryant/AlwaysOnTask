package com.lcbryant.alwaysontask.core.data.common

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val isOnline: Flow<Boolean>
}