package com.daily.vitals.domain.user.repository

import com.daily.vitals.domain.user.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserById(id: String): Flow<User?>
    suspend fun addUser(user: User)
}
