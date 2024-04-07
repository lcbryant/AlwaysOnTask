package com.lcbryant.alwaysontask.di

import com.lcbryant.alwaysontask.data.local.LocalDatabase
import com.lcbryant.alwaysontask.data.local.dao.TaskDao
import com.lcbryant.alwaysontask.data.local.dao.UserDao
import dagger.Provides

@Provides
fun provideTaskDao(database: LocalDatabase): TaskDao = database.taskDao()

@Provides
fun provideUserDao(database: LocalDatabase): UserDao = database.userDao()
