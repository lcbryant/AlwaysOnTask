package com.lcbryant.alwaysontask.core.data.network.model

import com.google.firebase.firestore.DocumentId

data class NetworkUser(
    @DocumentId val id: String = "",
    val localId: Int,
    val name: NetworkUserName,
    val email: String,
    val createdAt: String,
    val updatedAt: String,
    @field:JvmField
    val isAnonymous: Boolean = false,
)

data class NetworkUserName(
    val first: String,
    val last: String,
)
