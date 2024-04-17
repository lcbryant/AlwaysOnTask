package com.lcbryant.alwaysontask.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lcbryant.alwaysontask.core.data.common.TODO_TASK_COMPLETED_AT_COLUMN_NAME
import com.lcbryant.alwaysontask.core.data.common.TODO_TASK_CREATED_AT_COLUMN_NAME
import com.lcbryant.alwaysontask.core.data.common.TODO_TASK_DUE_DATE_COLUMN_NAME
import com.lcbryant.alwaysontask.core.data.common.TODO_TASK_DUE_TIME_COLUMN_NAME
import com.lcbryant.alwaysontask.core.data.common.TODO_TASK_FIREBASE_ID_COLUMN_NAME
import com.lcbryant.alwaysontask.core.data.common.TODO_TASK_FIREBASE_USER_ID_COLUMN_NAME
import com.lcbryant.alwaysontask.core.data.common.TODO_TASK_IS_SCHEDULED_COLUMN_NAME
import com.lcbryant.alwaysontask.core.data.common.TODO_TASK_LOCAL_USER_ID_COLUMN_NAME
import com.lcbryant.alwaysontask.core.data.common.TODO_TASK_PROGRESS_STATUS_COLUMN_NAME
import com.lcbryant.alwaysontask.core.data.common.TODO_TASK_TABLE_NAME
import com.lcbryant.alwaysontask.core.data.common.TODO_TASK_UPDATED_AT_COLUMN_NAME
import com.lcbryant.alwaysontask.core.model.TodoTaskProgressStatus
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Entity(tableName = TODO_TASK_TABLE_NAME)
data class TodoTaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(TODO_TASK_FIREBASE_ID_COLUMN_NAME) val firebaseId: String? = "",
    @ColumnInfo(TODO_TASK_LOCAL_USER_ID_COLUMN_NAME) val userId: Int?,
    @ColumnInfo(TODO_TASK_FIREBASE_USER_ID_COLUMN_NAME) val firebaseUserId: String? = "",

    val content: String,
    val duration: Duration,

    @ColumnInfo(TODO_TASK_PROGRESS_STATUS_COLUMN_NAME) val progressStatus: TodoTaskProgressStatus,

    @ColumnInfo(TODO_TASK_IS_SCHEDULED_COLUMN_NAME) val isScheduled: Boolean? = false,
    @ColumnInfo(TODO_TASK_DUE_DATE_COLUMN_NAME) val dueDate: LocalDate? = null,
    @ColumnInfo(TODO_TASK_DUE_TIME_COLUMN_NAME) val dueTime: LocalTime? = null,

    @ColumnInfo(TODO_TASK_COMPLETED_AT_COLUMN_NAME) val completedAt: LocalDateTime?,
    @ColumnInfo(TODO_TASK_CREATED_AT_COLUMN_NAME) val createdAt: LocalDateTime,
    @ColumnInfo(TODO_TASK_UPDATED_AT_COLUMN_NAME) val updatedAt: LocalDateTime,
)
