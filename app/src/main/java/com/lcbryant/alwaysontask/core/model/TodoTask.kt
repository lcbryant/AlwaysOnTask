package com.lcbryant.alwaysontask.core.model

import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

// need to revise this data class to only include fields necessary for app
// functionality external to the data layer
data class TodoTask(
    // id is the primary key to Room database; set to 0 for new tasks
    val id: Int = 0,
    // content is the main text of the task
    val content: String = "",
    val duration: Duration,

    val isComplete: Boolean = false,
    val progressStatus: TodoTaskProgressStatus = TodoTaskProgressStatus.NOT_STARTED,

    val isScheduled: Boolean = false,
    val dueDate: LocalDate? = null,
    val dueTime: LocalTime? = null,

    val completedAt: LocalDateTime? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
