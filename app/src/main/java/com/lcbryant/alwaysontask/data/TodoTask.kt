package com.lcbryant.alwaysontask.data

data class TodoTask(
    val localId: Int,
    val firebaseId: String = "",
    val localUserId: Int,
    val firebaseUserId: String = "",
    val content: String,
    val notes: String,
    val completed: Boolean,
    val inProgress: Boolean,
    val allDay: Boolean,
    val timeCreated: String,
    val duration: Int,
    val durationPaused: Boolean,
    val scheduled: Boolean,
    val scheduledDate: String,
    val scheduledStartTime: String,
)
