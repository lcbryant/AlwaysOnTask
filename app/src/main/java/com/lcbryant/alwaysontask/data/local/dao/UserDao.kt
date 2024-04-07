package com.lcbryant.alwaysontask.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.lcbryant.alwaysontask.data.local.entity.NameTuple
import com.lcbryant.alwaysontask.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    // reads
    @Query("SELECT * FROM user")
    fun observeAll(): Flow<UserEntity>

    @Query("SELECT first_name, last_name FROM user")
    fun getFullName(): Flow<NameTuple>

    // writes
    @Update
    suspend fun update(userEntity: UserEntity)

    @Insert
    suspend fun insert(userEntity: UserEntity)

    @Delete
    suspend fun delete(userEntity: UserEntity)
}