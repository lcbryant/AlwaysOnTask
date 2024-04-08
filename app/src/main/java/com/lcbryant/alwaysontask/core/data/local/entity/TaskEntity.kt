package com.lcbryant.alwaysontask.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("firebase_id") val firebaseId: String? = "",
    @ColumnInfo("local_user_id") val userId: Int,
    @ColumnInfo("firebase_user_id") val firebaseUserId: String? = "",
    val content: String,
    val notes: String?,
    val completed: Boolean?,
    @ColumnInfo("in_progress") val inProgress: Boolean? = false,
    @ColumnInfo("all_day") val allDay: Boolean? = false,
    @ColumnInfo("time_created") val timeCreated: String,
    @ColumnInfo("duration") val duration: Int? = null,
    @ColumnInfo("duration_paused") val durationPaused: Boolean? = true,
    @ColumnInfo("scheduled") val scheduled: Boolean? = false,
    @ColumnInfo("scheduled_date") val scheduledDate: String? = null,
    @ColumnInfo("scheduled_start_time") val scheduledStartTime: String? = null,
)
