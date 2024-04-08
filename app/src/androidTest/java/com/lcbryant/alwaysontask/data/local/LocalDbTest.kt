package com.lcbryant.alwaysontask.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lcbryant.alwaysontask.common.TimeUtil
import com.lcbryant.alwaysontask.core.data.local.LocalDatabase
import com.lcbryant.alwaysontask.core.data.local.dao.TaskDao
import com.lcbryant.alwaysontask.core.data.local.dao.UserDao
import com.lcbryant.alwaysontask.core.data.local.entity.UserEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class LocalDbTest {
    private lateinit var taskDao: TaskDao
    private lateinit var userDao: UserDao
    private lateinit var db: LocalDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            LocalDatabase::class.java
        ).build()
        userDao = db.userDao()
        taskDao = db.taskDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndRead() = runBlocking {
        val time = TimeUtil()
        val userEntity = UserEntity(
            1,
            createdAt = time.getCurrentDateTime(),
            updatedAt = time.getCurrentDateTime(),
        )

        userDao.insert(userEntity)

        val userFlow = userDao.observeAll()
        val userRead = userFlow.first()
        assertThat(userRead, equalTo(userEntity))
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadFullName() = runBlocking {
        val time = TimeUtil()
        val userEntity = UserEntity(
            1,
            createdAt = time.getCurrentDateTime(),
            updatedAt = time.getCurrentDateTime(),
            firstName = "John",
            lastName = "Doe"
        )

        userDao.insert(userEntity)

        val nameTupleFlow = userDao.getFullName()
        val nameTuple = nameTupleFlow.first()
        assertThat(nameTuple.firstName, equalTo(userEntity.firstName))
        assertThat(nameTuple.lastName, equalTo(userEntity.lastName))
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndUpdate() = runBlocking {
        val time = TimeUtil()
        val userEntity = UserEntity(
            1,
            createdAt = time.getCurrentDateTime(),
            updatedAt = time.getCurrentDateTime(),
            firstName = "John",
            lastName = "Doe"
        )

        userDao.insert(userEntity)

        val userFlow = userDao.observeAll()
        val userRead = userFlow.first()
        assertThat(userRead, equalTo(userEntity))

        val updatedUserEntity = UserEntity(
            1,
            createdAt = userRead.createdAt,
            updatedAt = time.getCurrentDateTime(),
            firstName = "Jane",
            lastName = "Doe"
        )

        userDao.update(updatedUserEntity)

        val updatedUserFlow = userDao.observeAll()
        val updatedUserRead = updatedUserFlow.first()
        assertThat(updatedUserRead, equalTo(updatedUserEntity))
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndDelete() = runBlocking {
        val time = TimeUtil()
        val userEntity = UserEntity(
            1,
            createdAt = time.getCurrentDateTime(),
            updatedAt = time.getCurrentDateTime(),
            firstName = "John",
            lastName = "Doe"
        )

        userDao.insert(userEntity)

        val userFlow = userDao.observeAll()
        val userRead = userFlow.first()
        assertThat(userRead, equalTo(userEntity))

        userDao.delete(userEntity)

        val userFlowAfterDelete = userDao.observeAll()
        val userReadAfterDelete = userFlowAfterDelete.first()
        assertThat(userReadAfterDelete, equalTo(null))
    }
}