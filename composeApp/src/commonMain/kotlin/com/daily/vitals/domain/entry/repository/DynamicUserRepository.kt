package com.daily.vitals.domain.entry.repository

import com.daily.vitals.domain.user.model.User
import com.daily.vitals.domain.user.repository.FirestoreUserRepository
import com.daily.vitals.domain.user.repository.SqlDelightUserRepository
import com.daily.vitals.domain.user.repository.UserRepository
import com.daily.vitals.feature.UserSessionProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class DynamicUserRepository(
    private val firestoreRepo: FirestoreUserRepository,
    private val localRepo: SqlDelightUserRepository,
    private val sessionProvider: UserSessionProvider
): UserRepository {

    private suspend fun getRepository(): UserRepository {
        return if (sessionProvider.isLocal()) localRepo else firestoreRepo
    }

    override fun getUserById(id: String): Flow<User?> = flow {
        val repository = getRepository()
        emitAll(repository.getUserById(id))
    }

    override suspend fun addUser(user: User) {
        getRepository().addUser(user)
    }
}
