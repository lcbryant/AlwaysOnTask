package com.lcbryant.alwaysontask.core.model

data class User(
    val firebaseId: String? = "",
    val localId: Int? = 0,
    val name: NameTuple = NameTuple("", ""),
    val email: String? = "",
    val createdAt: String,
    val updatedAt: String,
    val isAnonymous: Boolean = false,
)

data class NameTuple(
    val first: String,
    val last: String,
)
