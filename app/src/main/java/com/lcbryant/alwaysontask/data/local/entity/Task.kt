package com.lcbryant.alwaysontask.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val content: String,
    val notes: String,
    val completed: Boolean,
    @ColumnInfo("time_created") val timeCreated: String,
    @ColumnInfo("scheduled_date") val scheduledDate: Long,
)
