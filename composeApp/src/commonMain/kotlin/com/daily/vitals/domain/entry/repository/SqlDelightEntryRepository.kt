package com.daily.vitals.domain.entry.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneNotNull
import com.daily.vitals.composeApp.database.DailyVitalsDatabase
import com.daily.vitals.domain.entry.model.Entry
import com.daily.vitals.domain.entry.model.toEntryModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SqlDelightEntryRepository(
    private val database: DailyVitalsDatabase,
    private val dispatcher: CoroutineDispatcher
): DailyEntryRepository {

    override fun getDailyEntriesByUser(userId: String): Flow<List<Entry>> {
        return database.dailyVitalsDatabaseQueries
            .selectEntriesByUser(userId)
            .asFlow()
            .mapToList(dispatcher)
            .map { dbEntries -> dbEntries.map { it.toEntryModel() } }
    }

    override fun getDailyEntryById(userId: String, entryId: String): Flow<Entry> {
        return database.dailyVitalsDatabaseQueries
            .selectEntryByUserAndId(entry_id = entryId, user_id = userId)
            .asFlow()
            .mapToOneNotNull(dispatcher)
            .map { it.toEntryModel() }
    }

    override suspend fun addDailyEntry(userId: String, entry: Entry) {
        database.dailyVitalsDatabaseQueries.insertEntry(
            entry_id = entry.id,
            user_id = userId,
            fasting = entry.fasting.toLong(),
            post_meal = entry.postMeal.toLong(),
            exercise = entry.exercise,
            sleep = entry.sleep.toLong(),
            weight = entry.weight.toDouble()
        )
    }

    override suspend fun updateDailyEntry(userId: String, entry: Entry) {
        database.dailyVitalsDatabaseQueries.updateEntry(
            entry_id = entry.id,
            user_id = userId,
            fasting = entry.fasting.toLong(),
            post_meal = entry.postMeal.toLong(),
            exercise = entry.exercise,
            sleep = entry.sleep.toLong(),
            weight = entry.weight.toDouble()
        )
    }

    override suspend fun deleteDailyEntry(userId: String, entryId: String) {
        database.dailyVitalsDatabaseQueries.deleteEntry(
                entry_id = entryId,
                user_id = userId
        )
    }
}
