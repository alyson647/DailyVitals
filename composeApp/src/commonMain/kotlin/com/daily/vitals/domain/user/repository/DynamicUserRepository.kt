package com.daily.vitals.domain.user.repository

import com.daily.vitals.domain.user.model.User
import com.daily.vitals.feature.UserSessionProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class DynamicUserRepository(
    private val firestoreRepo: FirestoreUserRepository,
    private val localRepo: SqlDelightUserRepository,
    private val sessionProvider: UserSessionProvider
): UserRepository {

    override fun getUserById(id: String): Flow<User?> = flow {
        if (sessionProvider.isLocal()) {
            emitAll(localRepo.getUserById(id))
        } else {
            try {
                emitAll(firestoreRepo.getUserById(id))
            } catch (_: Exception) {
                emitAll(localRepo.getUserById(id))
            }
        }
    }

    override suspend fun addUser(user: User) {
        if (sessionProvider.isLocal()) {
            localRepo.addUser(user)
        } else {
            val localSuccess = try {
                localRepo.addUser(user)
                true
            } catch (_: Exception) {
                false
            }

            if (localSuccess) {
                try {
                    firestoreRepo.addUser(user)
                } catch (_: Exception) {
                    // firebase failure is silent
                }
            }
        }
    }
}