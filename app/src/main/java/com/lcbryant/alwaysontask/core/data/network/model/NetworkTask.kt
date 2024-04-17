package com.lcbryant.alwaysontask.core.data.network.model

import com.google.firebase.firestore.DocumentId
import com.lcbryant.alwaysontask.core.model.TodoTaskProgressStatus
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


data class NetworkTask(
    @DocumentId val id: String = "",
    val localId: Int, // id is the primary key to Room database
    val firebaseUserId: String? = "",
    val localUserId: Int,

    val content: String,
    val duration: Duration,

    val isComplete: Boolean = false,
    val progressStatus: TodoTaskProgressStatus,

    val isScheduled: Boolean = false,
    val dueDate: LocalDate? = null,
    val dueTime: LocalTime? = null,

    val completedAt: LocalDateTime? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
