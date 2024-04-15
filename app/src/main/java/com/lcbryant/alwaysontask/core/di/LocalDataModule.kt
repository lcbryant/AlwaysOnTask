package com.lcbryant.alwaysontask.core.di

import android.content.Context
import com.lcbryant.alwaysontask.core.data.local.dao.TodoTaskDao
import com.lcbryant.alwaysontask.core.data.local.dao.UserDao
import com.lcbryant.alwaysontask.core.database.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    fun provideUserDao(database: LocalDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideTodoTaskDao(database: LocalDatabase): TodoTaskDao {
        return database.todoTaskDao()
    }

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): LocalDatabase {
        return LocalDatabase.getDb(context)
    }
}