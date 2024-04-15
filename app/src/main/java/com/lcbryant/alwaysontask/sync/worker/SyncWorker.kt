package com.lcbryant.alwaysontask.sync.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.lcbryant.alwaysontask.core.data.repository.TodoTaskRepository
import com.lcbryant.alwaysontask.core.data.repository.UserRepository
import com.lcbryant.alwaysontask.sync.status.SyncSubscriber
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
internal class SyncWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val todoTaskRepo: TodoTaskRepository,
    private val userRepo: UserRepository,
    private val syncSubscriber: SyncSubscriber
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        TODO("Not yet implemented")
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return super.getForegroundInfo()
    }

    companion object {
        // implement startup sync function
    }
}

