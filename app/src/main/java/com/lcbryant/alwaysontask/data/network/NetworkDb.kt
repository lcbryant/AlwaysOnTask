package com.lcbryant.alwaysontask.data.network

import com.google.firebase.firestore.FirebaseFirestore

object NetworkDb {
    val instance: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
}
