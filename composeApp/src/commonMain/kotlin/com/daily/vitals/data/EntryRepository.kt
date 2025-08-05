package com.daily.vitals.data

import com.daily.vitals.model.Entry
import kotlinx.coroutines.flow.Flow

interface EntryRepository {
    fun getEntriesByUser(userId: String): Flow<List<Entry>>
    fun getEntryById(userId: String, entryId: String): Flow<Entry?>
    suspend fun addEntry(userId: String, entry: Entry)
    suspend fun updateEntry(userId: String, entry: Entry)
    suspend fun deleteEntry(userId: String, entryId: String)
}
