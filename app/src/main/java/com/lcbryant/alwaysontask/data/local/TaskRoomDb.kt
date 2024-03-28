package com.lcbryant.alwaysontask.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lcbryant.alwaysontask.data.local.dao.TaskDao
import com.lcbryant.alwaysontask.data.local.entity.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskRoomDb : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        // Singleton prevents multiple instances of database opening at same time
        @Volatile
        private var INSTANCE: TaskRoomDb? = null

        fun getDb(context: Context): TaskRoomDb {
            // If the INSTANCE is not null, then return it, otherwise create the db
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskRoomDb::class.java,
                    "task_db"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}