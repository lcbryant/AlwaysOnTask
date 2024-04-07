package com.lcbryant.alwaysontask.data

import androidx.lifecycle.asLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.lcbryant.alwaysontask.data.local.dao.UserDao
import com.lcbryant.alwaysontask.data.local.entity.NameTuple
import com.lcbryant.alwaysontask.data.local.entity.UserEntity
import com.lcbryant.alwaysontask.data.network.model.NetworkUser
import com.lcbryant.alwaysontask.di.DefaultDispatcher
import com.lcbryant.alwaysontask.extension.asNetworkUser
import com.lcbryant.alwaysontask.extension.asUserEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val firestore: FirebaseFirestore,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) {
    // exposing data from single source of truth, the local database
    private val user = userDao.observeAll().asLiveData()

    suspend fun insert(user: User) {
        // Insert userEntity into local database
        val userEntity: UserEntity = user.asUserEntity()
        userDao.insert(userEntity)

        // Insert NetworkUser into Firestore
        val networkUser: NetworkUser = user.asNetworkUser()
    }

    suspend fun update(user: User) {
        // Update userEntity in local database
        val userEntity: UserEntity = user.asUserEntity()
        userDao.update(userEntity)

        // Update NetworkUser in Firestore
        val networkUser: NetworkUser = user.asNetworkUser()
    }

    suspend fun delete(user: User) {
        // Delete userEntity from local
        val userEntity: UserEntity = user.asUserEntity()
        userDao.delete(userEntity)

        // Delete NetworkUser from Firestore
        val networkUser: NetworkUser = user.asNetworkUser()
    }

    fun getFullName(): Flow<NameTuple> {
        return userDao.getFullName()
    }
}