package com.lcbryant.alwaysontask.core.database

import androidx.room.TypeConverter
import com.lcbryant.alwaysontask.core.model.TodoTaskProgressStatus
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class Converters {
    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime): String {
        return value.toString()
    }

    @TypeConverter
    fun toLocalDateTime(value: String): LocalDateTime {
        return LocalDateTime.parse(value)
    }

    @TypeConverter
    fun fromLocalDateTimeNullable(value: LocalDateTime?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toLocalDateTimeNullable(value: String?): LocalDateTime? {
        return value?.let { LocalDateTime.parse(it) }
    }

    @TypeConverter
    fun fromLocalDate(value: LocalDate): String {
        return value.toString()
    }

    @TypeConverter
    fun toLocalDate(value: String): LocalDate {
        return LocalDate.parse(value)
    }

    @TypeConverter
    fun fromLocalTime(value: LocalTime): String {
        return value.toString()
    }

    @TypeConverter
    fun toLocalTime(value: String): LocalTime {
        return LocalTime.parse(value)
    }

    @TypeConverter
    fun fromProgressStatus(value: TodoTaskProgressStatus): String {
        return value.name
    }

    @TypeConverter
    fun toProgressStatus(value: String): TodoTaskProgressStatus {
        return TodoTaskProgressStatus.valueOf(value)
    }

    @TypeConverter
    fun fromDuration(value: Duration): Long {
        return value.toMinutes()
    }

    @TypeConverter
    fun toDuration(value: Long): Duration {
        return Duration.ofMinutes(value)
    }
}