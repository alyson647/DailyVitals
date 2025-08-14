package com.daily.vitals.domain.entry.repository

import com.daily.vitals.domain.entry.model.Entry
import com.daily.vitals.feature.UserSessionProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class DynamicEntryRepository(
    private val firestoreRepo: FirestoreEntryRepository,
    private val localRepo: SqlDelightEntryRepository,
    private val sessionProvider: UserSessionProvider
): DailyEntryRepository {

    private suspend fun getRepository(): DailyEntryRepository {
        return if (sessionProvider.isLocal()) localRepo else firestoreRepo
    }

    override fun getDailyEntriesByUser(userId: String): Flow<List<Entry>> = flow {
        val repository = getRepository()
        emitAll(repository.getDailyEntriesByUser(userId))
    }

    override fun getDailyEntryById(userId: String, entryId: String): Flow<Entry?> = flow {
        val repository = getRepository()
        emitAll(repository.getDailyEntryById(userId = userId, entryId = entryId))
    }

    override suspend fun addDailyEntry(userId: String, entry: Entry) {
        getRepository().addDailyEntry(userId = userId, entry = entry)
    }

    override suspend fun updateDailyEntry(userId: String, entry: Entry) {
        getRepository().updateDailyEntry(userId = userId, entry = entry)
    }

    override suspend fun deleteDailyEntry(userId: String, entryId: String) {
        getRepository().deleteDailyEntry(userId = userId, entryId = entryId)
    }
}
