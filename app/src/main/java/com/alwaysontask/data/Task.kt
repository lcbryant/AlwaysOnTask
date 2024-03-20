package com.alwaysontask.data

data class Task(
    val id: String,
    val label: String? = "New task",
    val notes: String? = "Write your notes here",
    val completed: Boolean = false,
    )


