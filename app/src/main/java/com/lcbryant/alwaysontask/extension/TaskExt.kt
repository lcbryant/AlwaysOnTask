package com.lcbryant.alwaysontask.extension

import com.lcbryant.alwaysontask.data.TodoTask
import com.lcbryant.alwaysontask.data.local.entity.TaskEntity
import com.lcbryant.alwaysontask.data.network.model.NetworkTask

fun NetworkTask.asTaskEntity(): TaskEntity {
    return TaskEntity(
        id = localId,
        firebaseId = id,
        userId = localUserId,
        firebaseUserId = userId,
        content = content,
        notes = notes,
        completed = completed,
        inProgress = inProgress,
        allDay = allDay,
        timeCreated = timeCreated,
        duration = duration,
        durationPaused = durationPaused,
        scheduled = scheduled,
        scheduledDate = scheduledDate,
        scheduledStartTime = scheduledStartTime,
    )
}

fun TaskEntity.asNetworkTask(): NetworkTask {
    return NetworkTask(
        id = firebaseId ?: "",
        localId = id,
        userId = firebaseUserId ?: "",
        localUserId = userId,
        content = content,
        notes = notes ?: "",
        completed = completed ?: false,
        inProgress = inProgress ?: false,
        allDay = allDay ?: false,
        timeCreated = timeCreated,
        duration = duration ?: 0,
        durationPaused = durationPaused ?: false,
        scheduled = scheduled ?: false,
        scheduledDate = scheduledDate ?: "",
        scheduledStartTime = scheduledStartTime ?: "",
    )
}

fun NetworkTask.asTodoTask(): TodoTask {
    return TodoTask(
        firebaseId = id,
        localId = localId,
        firebaseUserId = userId,
        localUserId = localUserId,
        content = content,
        notes = notes,
        completed = completed,
        inProgress = inProgress,
        allDay = allDay,
        timeCreated = timeCreated,
        duration = duration,
        durationPaused = durationPaused,
        scheduled = scheduled,
        scheduledDate = scheduledDate,
        scheduledStartTime = scheduledStartTime,
    )
}

fun TodoTask.asNetworkTask(): NetworkTask {
    return NetworkTask(
        id = firebaseId,
        localId = localId,
        userId = firebaseUserId,
        localUserId = localUserId,
        content = content,
        notes = notes,
        completed = completed,
        inProgress = inProgress,
        allDay = allDay,
        timeCreated = timeCreated,
        duration = duration,
        durationPaused = durationPaused,
        scheduled = scheduled,
        scheduledDate = scheduledDate,
        scheduledStartTime = scheduledStartTime,
    )
}

fun TaskEntity.asTodoTask(): TodoTask {
    return TodoTask(
        firebaseId = firebaseId ?: "",
        localId = id,
        firebaseUserId = firebaseUserId ?: "",
        localUserId = userId,
        content = content,
        notes = notes ?: "",
        completed = completed ?: false,
        inProgress = inProgress ?: false,
        allDay = allDay ?: false,
        timeCreated = timeCreated,
        duration = duration ?: 0,
        durationPaused = durationPaused ?: false,
        scheduled = scheduled ?: false,
        scheduledDate = scheduledDate ?: "",
        scheduledStartTime = scheduledStartTime ?: "",
    )
}

fun TodoTask.asTaskEntity(): TaskEntity {
    return TaskEntity(
        id = localId,
        firebaseId = firebaseId,
        userId = localUserId,
        firebaseUserId = firebaseUserId,
        content = content,
        notes = notes,
        completed = completed,
        inProgress = inProgress,
        allDay = allDay,
        timeCreated = timeCreated,
        duration = duration,
        durationPaused = durationPaused,
        scheduled = scheduled,
        scheduledDate = scheduledDate,
        scheduledStartTime = scheduledStartTime,
    )
}