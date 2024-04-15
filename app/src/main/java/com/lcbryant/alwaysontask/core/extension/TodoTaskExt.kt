package com.lcbryant.alwaysontask.core.extension

import com.lcbryant.alwaysontask.core.data.local.entity.TodoTaskEntity
import com.lcbryant.alwaysontask.core.model.TodoTask

fun TodoTask.toEntity(): TodoTaskEntity {
    return TodoTaskEntity(
        id = this.id,
        userId = 0,
        content = this.content,
        notes = this.note,
        notesUpdatedAt = this.notesUpdatedAt,
        progressStatus = this.progressStatus,
        isScheduled = this.isScheduled,
        dueDate = this.dueDate,
        dueTime = this.dueTime,
        completedAt = this.completedAt,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
    )
}

fun TodoTaskEntity.toModel(): TodoTask {
    return TodoTask(
        id = this.id,
        content = this.content,
        note = this.notes ?: "",
        notesUpdatedAt = this.notesUpdatedAt,
        progressStatus = this.progressStatus,
        isScheduled = this.isScheduled == true,
        dueDate = this.dueDate,
        dueTime = this.dueTime,
        completedAt = this.completedAt,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
    )
}
