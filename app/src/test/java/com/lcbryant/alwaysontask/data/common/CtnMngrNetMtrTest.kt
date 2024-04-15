package com.lcbryant.alwaysontask.data.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lcbryant.alwaysontask.core.data.common.ConnectivityManagerNetworkMonitor
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ConnectivityManagerNetworkMonitorTest {

    private val context = mockk<Context>(relaxed = true)
    private val connectivityManager = mockk<ConnectivityManager>(relaxed = true)

    @Test
    fun `test network monitor when online`() = runBlocking {
        // Set up the context to return the mocked ConnectivityManager
        every { context.getSystemService(Context.CONNECTIVITY_SERVICE) } returns connectivityManager

        // Mock the behavior of ConnectivityManager to simulate an online state
        every { connectivityManager.activeNetwork } returns mockk()
        every { connectivityManager.getNetworkCapabilities(any()) } returns mockk<NetworkCapabilities>().apply {
            every { hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) } returns true
        }

        val networkMonitor = ConnectivityManagerNetworkMonitor(context)
        val isOnline = networkMonitor.isOnline.first()

        assertEquals(true, isOnline)
    }
}