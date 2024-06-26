package com.lcbryant.alwaysontask.core.extension

import com.lcbryant.alwaysontask.core.data.local.entity.UserEntity
import com.lcbryant.alwaysontask.core.data.network.model.NetworkUser
import com.lcbryant.alwaysontask.core.data.network.model.NetworkUserName
import com.lcbryant.alwaysontask.core.model.NameTuple
import com.lcbryant.alwaysontask.core.model.User

fun NetworkUser.asUserEntity(): UserEntity {
    return UserEntity(
        id = localId,
        firebaseId = id,
        firstName = name.first,
        lastName = name.last,
        email = email,
        createdAt = createdAt,
        updatedAt = updatedAt,
        isAnonymous = isAnonymous,
    )
}

fun UserEntity.asNetworkUser(): NetworkUser {
    return NetworkUser(
        id = firebaseId ?: "",
        localId = id,
        name = NetworkUserName(
            first = firstName ?: "",
            last = lastName ?: "",
        ),
        email = email ?: "",
        createdAt = createdAt,
        updatedAt = updatedAt,
        isAnonymous = isAnonymous,
    )
}

fun NetworkUser.asUser(): User {
    return User(
        firebaseId = id,
        localId = localId,
        name = NameTuple(
            first = name.first,
            last = name.last,
        ),
        email = email,
        createdAt = createdAt,
        updatedAt = updatedAt,
        isAnonymous = isAnonymous,
    )
}

fun User.asNetworkUser(): NetworkUser {
    return NetworkUser(
        id = firebaseId ?: "",
        localId = localId ?: 0,
        name = NetworkUserName(
            first = name.first,
            last = name.last,
        ),
        email = email ?: "",
        createdAt = createdAt,
        updatedAt = updatedAt,
        isAnonymous = isAnonymous,
    )
}

fun UserEntity.asUser(): User {
    return User(
        firebaseId = firebaseId,
        localId = id,
        name = NameTuple(
            first = firstName ?: "",
            last = lastName ?: "",
        ),
        email = email ?: "",
        createdAt = createdAt,
        updatedAt = updatedAt,
        isAnonymous = isAnonymous,
    )
}

fun User.asUserEntity(): UserEntity {
    return UserEntity(
        id = localId ?: 0,
        firebaseId = firebaseId,
        firstName = name.first,
        lastName = name.last,
        email = email,
        createdAt = createdAt,
        updatedAt = updatedAt,
        isAnonymous = isAnonymous,
    )
}

