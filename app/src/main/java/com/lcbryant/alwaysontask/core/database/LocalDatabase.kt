package com.lcbryant.alwaysontask.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lcbryant.alwaysontask.core.data.common.DATABASE_NAME
import com.lcbryant.alwaysontask.core.data.local.dao.TodoTaskDao
import com.lcbryant.alwaysontask.core.data.local.dao.UserDao
import com.lcbryant.alwaysontask.core.data.local.entity.TodoTaskEntity
import com.lcbryant.alwaysontask.core.data.local.entity.UserEntity

@Database(entities = [TodoTaskEntity::class, UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun todoTaskDao(): TodoTaskDao
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
                    DATABASE_NAME
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}