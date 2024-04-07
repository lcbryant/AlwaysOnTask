package com.lcbryant.alwaysontask.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lcbryant.alwaysontask.data.local.dao.TaskDao
import com.lcbryant.alwaysontask.data.local.dao.UserDao
import com.lcbryant.alwaysontask.data.local.entity.TaskEntity
import com.lcbryant.alwaysontask.data.local.entity.UserEntity

@Database(entities = [TaskEntity::class, UserEntity::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun userDao(): UserDao

    companion object {
        // Singleton prevents multiple instances of database opening at same time
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getDb(context: Context): LocalDatabase {
            // If the INSTANCE is not null, then return it, otherwise create the db
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    "task_db"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}