package com.daily.vitals.domain.entry.repository

import com.daily.vitals.domain.entry.model.Entry
import kotlinx.coroutines.flow.Flow

interface DailyEntryRepository {
    fun getDailyEntriesByUser(userId: String): Flow<List<Entry>>
    fun getDailyEntryById(userId: String, entryId: String): Flow<Entry?>
    suspend fun addDailyEntry(userId: String, entry: Entry)
    suspend fun updateDailyEntry(userId: String, entry: Entry)
    suspend fun deleteDailyEntry(userId: String, entryId: String)
}
