package com.lcbryant.alwaysontask.feature.login

import com.lcbryant.alwaysontask.core.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: String
    val hasUser: Boolean

    val currentUserEntity: Flow<UserEntity>

    suspend fun authenticate(email: String, password: String)
    suspend fun sendRecoveryEmail(email: String)
    suspend fun linkAccount(email: String, password: String)
    suspend fun deleteAccount()
    suspend fun logOut()
}