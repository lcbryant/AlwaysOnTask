package com.lcbryant.alwaysontask.feature.login

import com.google.firebase.auth.FirebaseAuth
import com.lcbryant.alwaysontask.core.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(private val auth: FirebaseAuth) : AccountService {
    override val currentUserEntity: Flow<UserEntity>
        get() = callbackFlow {

        }

    override val currentUserId: String
        get() = auth.currentUser?.uid ?: ""

    override val hasUser: Boolean
        get() = auth.currentUser != null

    override suspend fun authenticate(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun sendRecoveryEmail(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    override suspend fun logOut() {
        if (auth.currentUser?.isAnonymous == true) {
            auth.currentUser!!.delete()
        }

        auth.signOut()

        // sign user back in anonymously
    }

    override suspend fun linkAccount(email: String, password: String) {

    }
}