package com.lcbryant.alwaysontask.data.network.model


data class NetworkTask(
    val id: Int,
    val content: String,
    val notes: String,
    val completed: Boolean,
    val timeCreated: String,
    val scheduledDate: Long,
)
