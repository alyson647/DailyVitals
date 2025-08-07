package com.daily.vitals.domain.user.repository

import com.daily.vitals.domain.user.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getString(): String
    fun getUsers(): Flow<List<User>>
    fun getUserById(id: String): Flow<User?>
    suspend fun addUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
}
