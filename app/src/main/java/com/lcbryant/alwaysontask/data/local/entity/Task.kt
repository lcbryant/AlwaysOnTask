package com.lcbryant.alwaysontask.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val content: String,
    val notes: String,
    val completed: Boolean,
    val timeCreated: String,
    val scheduledDate: Long,
)
