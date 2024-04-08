package com.lcbryant.alwaysontask.core.data

data class TodoTask(
    val localId: Int,
    val firebaseId: String = "",
    val localUserId: Int,
    val firebaseUserId: String = "",
    val content: String,
    val notes: String,
    val completed: Boolean = false,
    val inProgress: Boolean = false,
    val allDay: Boolean = false,
    val timeCreated: String,
    // duration in minutes
    val duration: Int,
    val durationPaused: Boolean = false,
    val scheduled: Boolean = false,
    val scheduledDate: String,
    val scheduledStartTime: String,
)
