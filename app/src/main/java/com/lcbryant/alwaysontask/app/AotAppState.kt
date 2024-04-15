package com.lcbryant.alwaysontask.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.lcbryant.alwaysontask.core.data.common.NetworkMonitor
import kotlinx.coroutines.CoroutineScope

@Stable
class AotAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
    networkMonitor: NetworkMonitor,
) {

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val isOnline = networkMonitor.isOnline
}