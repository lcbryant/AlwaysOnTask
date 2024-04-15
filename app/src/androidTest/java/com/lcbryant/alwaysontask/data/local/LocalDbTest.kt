package com.lcbryant.alwaysontask.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lcbryant.alwaysontask.common.DateTimeProviderImpl
import com.lcbryant.alwaysontask.common.TimeUtil
import com.lcbryant.alwaysontask.core.data.local.dao.TodoTaskDao
import com.lcbryant.alwaysontask.core.data.local.dao.UserDao
import com.lcbryant.alwaysontask.core.data.local.entity.UserEntity
import com.lcbryant.alwaysontask.core.data.repository.TodoTaskRepository
import com.lcbryant.alwaysontask.core.data.repository.UserRepository
import com.lcbryant.alwaysontask.core.database.LocalDatabase
import com.lcbryant.alwaysontask.core.model.TodoTask
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
    private lateinit var todoTaskDao: TodoTaskDao
    private lateinit var userDao: UserDao
    private lateinit var db: LocalDatabase
    private lateinit var todoRepo: TodoTaskRepository
    private lateinit var userRepo: UserRepository

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            LocalDatabase::class.java
        ).build()
        userDao = db.userDao()
        todoTaskDao = db.todoTaskDao()
        todoRepo = TodoTaskRepository(todoTaskDao)
        userRepo = UserRepository(userDao)
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

    @Test
    @Throws(Exception::class)
    fun writeTodoAndRead() = runBlocking {
        val dateTimeProv = DateTimeProviderImpl()
        val todoTask = TodoTask(
            id = 1,
            content = "Test Task",
            note = "Test notes",
            createdAt = dateTimeProv.now(),
            updatedAt = dateTimeProv.now()
        )

        todoRepo.insertTask(todoTask)

        val todoFlow = todoRepo.getAllTasksStream()
        val todoRead = todoFlow.first()[0]
        assertThat(todoRead, equalTo(todoTask))
    }
}