package com.lcbryant.alwaysontask.core.data.network

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSource {
    @Provides
    fun firestore(): FirebaseFirestore = Firebase.firestore

    private fun getUser(userId: String) {
        firestore().collection("users").document(userId).get()
    }

    private fun getTask(taskId: String) {
        firestore().collection("tasks").document(taskId).get()
    }
}
