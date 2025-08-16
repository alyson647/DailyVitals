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

    override fun getDailyEntriesByUser(userId: String): Flow<List<Entry>> = flow {
        if (sessionProvider.isLocal()) {
            emitAll(localRepo.getDailyEntriesByUser(userId))
        } else {
            try {
                emitAll(firestoreRepo.getDailyEntriesByUser(userId))
            } catch (_: Exception) {
                emitAll(localRepo.getDailyEntriesByUser(userId))
            }
        }
    }

    override fun getDailyEntryById(userId: String, entryId: String): Flow<Entry?> = flow {
        if (sessionProvider.isLocal()) {
            emitAll(localRepo.getDailyEntryById(userId = userId, entryId = entryId))
        } else {
            try {
                emitAll(firestoreRepo.getDailyEntryById(userId = userId, entryId = entryId))
            } catch (_: Exception) {
                emitAll(localRepo.getDailyEntryById(userId = userId, entryId = entryId))
            }
        }
    }

    override suspend fun addDailyEntry(userId: String, entry: Entry) {
        if (sessionProvider.isLocal()) {
            localRepo.addDailyEntry(userId = userId, entry = entry)
        } else {
            val localSuccess = try {
                localRepo.addDailyEntry(userId = userId, entry = entry)
                true
            } catch (_: Exception) {
                false
            }

            if (localSuccess) {
                try {
                    firestoreRepo.addDailyEntry(userId = userId, entry = entry)
                } catch (_: Exception) {
                    // silent failure
                }
            }
        }
    }

    override suspend fun updateDailyEntry(userId: String, entry: Entry) {
        if (sessionProvider.isLocal()) {
            localRepo.updateDailyEntry(userId = userId, entry = entry)
        } else {
            val localSuccess = try {
                localRepo.updateDailyEntry(userId = userId, entry = entry)
                true
            } catch (_: Exception) {
                false
            }

            if (localSuccess) {
                try {
                    firestoreRepo.updateDailyEntry(userId = userId, entry = entry)
                } catch (_: Exception) {
                    // silent failure
                }
            }
        }
    }

    override suspend fun deleteDailyEntry(userId: String, entryId: String) {
        if (sessionProvider.isLocal()) {
            localRepo.deleteDailyEntry(userId = userId, entryId = entryId)
        } else {
           val localSuccess = try {
               localRepo.deleteDailyEntry(userId = userId, entryId = entryId)
               true
           } catch (_: Exception) {
               false
           }

            if (localSuccess) {
                try {
                    firestoreRepo.deleteDailyEntry(userId = userId, entryId = entryId)
                } catch (_: Exception) {
                    // silent failure
                }
            }
        }
    }
}
