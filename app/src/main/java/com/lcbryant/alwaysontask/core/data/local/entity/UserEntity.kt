package com.lcbryant.alwaysontask.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("firebase_id") val firebaseId: String? = "",
    @ColumnInfo("first_name") val firstName: String? = null,
    @ColumnInfo("last_name") val lastName: String? = null,
    val email: String? = null,
    @ColumnInfo("created_at") val createdAt: String,
    @ColumnInfo("updated_at") val updatedAt: String,
    @ColumnInfo("is_anonymous") val isAnonymous: Boolean = false,
)

@Entity
data class NameTuple(
    @ColumnInfo("first_name") val firstName: String,
    @ColumnInfo("last_name") val lastName: String,
)


