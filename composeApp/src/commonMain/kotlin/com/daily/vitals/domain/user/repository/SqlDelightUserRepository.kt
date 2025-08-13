package com.daily.vitals.domain.user.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneNotNull
import com.daily.vitals.composeApp.database.DailyVitalsDatabase
import com.daily.vitals.domain.user.model.User
import com.daily.vitals.domain.user.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.CoroutineDispatcher

class SqlDelightUserRepository(
    private val database: DailyVitalsDatabase,
    private val dispatcher: CoroutineDispatcher
): UserRepository {

    override fun getUserById(id: String): Flow<User?> {
        return database.dailyVitalsDatabaseQueries
            .selectUserById(id)
            .asFlow()
            .mapToOneNotNull(dispatcher)
            .map { dbUser -> dbUser.toDomain() }
    }

    override suspend fun addUser(user: User) {
        database.dailyVitalsDatabaseQueries.insertUser(
            user_id = user.id,
            email = user.email,
            name = user.name,
            profile_picture = user.profilePicture
        )
    }
}
