package com.lcbryant.alwaysontask.data.network

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.sync.Mutex

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSource {
    private val accessMutex = Mutex()
    @Provides
    fun firestore(): FirebaseFirestore = Firebase.firestore

    suspend fun <T> access(action: suspend () -> T): T {
        accessMutex.lock()
        return try {
            action()
        } finally {
            accessMutex.unlock()
        }
    }

    suspend fun <T> accessFirestore(action: suspend FirebaseFirestore.() -> T): T {
        return access { firestore().action() }
    }


}
