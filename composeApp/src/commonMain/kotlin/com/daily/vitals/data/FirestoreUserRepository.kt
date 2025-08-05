package com.daily.vitals.data

import com.daily.vitals.model.USERS_COLLECTION
import com.daily.vitals.model.User
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.flow

class FirestoreUserRepository : UserRepository {

    private val firestore = Firebase.firestore

    override fun getUsers() = flow {
        firestore.collection(USERS_COLLECTION)
            .snapshots.collect { querySnapshot ->
                val users = querySnapshot.documents.map { documentSnapshot ->
                    documentSnapshot.data<User>()
                }
                emit(users)
            }
    }

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

    override suspend fun updateUser(user: User) {
        firestore.collection(USERS_COLLECTION)
            .document(user.id)
            .set(user)
    }

    override suspend fun deleteUser(user: User) {
        firestore.collection(USERS_COLLECTION)
            .document(user.id)
            .delete()
    }
}
