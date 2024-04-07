package com.lcbryant.alwaysontask.sync.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.WorkerParameters
import com.lcbryant.alwaysontask.data.TodoTaskRepository
import com.lcbryant.alwaysontask.data.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher

@HiltWorker
internal class SyncWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val todoTaskRepo: TodoTaskRepository,
    private val userRepo: UserRepository,

)

