package com.lcbryant.alwaysontask.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.lcbryant.alwaysontask.core.data.common.USER_TABLE_NAME
import com.lcbryant.alwaysontask.core.data.local.entity.NameTuple
import com.lcbryant.alwaysontask.core.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    // reads
    @Query("SELECT * FROM $USER_TABLE_NAME")
    fun observeAll(): Flow<UserEntity>

    @Query("SELECT first_name, last_name FROM $USER_TABLE_NAME")
    fun getFullName(): Flow<NameTuple>

    // writes
    @Update
    suspend fun update(userEntity: UserEntity)

    @Insert
    suspend fun insert(userEntity: UserEntity)

    @Delete
    suspend fun delete(userEntity: UserEntity)

    @Query("DELETE FROM $USER_TABLE_NAME")
    suspend fun nukeTable()
}