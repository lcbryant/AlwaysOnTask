package com.lcbryant.alwaysontask.core.data.network.model

import com.google.firebase.firestore.DocumentId


data class NetworkTask(
    @DocumentId val id: String = "",
    val localId: Int,
    val userId: String = "",
    val localUserId: Int,
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
