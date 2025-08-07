package com.daily.vitals.domain.user.repository

import com.daily.vitals.domain.USERS_COLLECTION
import com.daily.vitals.domain.user.model.User
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.flow

class FirestoreUserRepository(
    private val firestore: FirebaseFirestore
) : UserRepository {

    override fun getUserById(id: String) = flow {
        firestore.collection(USERS_COLLECTION)
            .document(id)
            .snapshots
            .collect { documentSnapshot ->
                emit(documentSnapshot.data<User>())
            }
    }

    override suspend fun addUser(user: User) {
        firestore.collection(USERS_COLLECTION)
            .document(user.id)
            .set(user)
    }
}
