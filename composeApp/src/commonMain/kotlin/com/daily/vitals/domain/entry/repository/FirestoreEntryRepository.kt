package com.daily.vitals.domain.entry.repository

import com.daily.vitals.domain.entry.model.Entry
import com.daily.vitals.domain.ENTRIES_COLLECTION
import com.daily.vitals.domain.USERS_COLLECTION
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.flow

class FirestoreEntryRepository(
    private val firestore: FirebaseFirestore
) : DailyEntryRepository {

    override fun getDailyEntriesByUser(userId: String) = flow {
        firestore.collection(USERS_COLLECTION)
            .document(userId)
            .collection(ENTRIES_COLLECTION)
            .snapshots
            .collect { querySnapshot ->
                val entries = querySnapshot.documents.map { documentSnapshot ->
                    documentSnapshot.data<Entry>()
                }
                emit(entries)
            }
    }

    override fun getDailyEntryById(userId: String, entryId: String) = flow {
        firestore.collection(USERS_COLLECTION)
            .document(userId)
            .collection(ENTRIES_COLLECTION)
            .document(entryId)
            .snapshots
            .collect { documentSnapshot ->
                emit(documentSnapshot.data<Entry>())
            }
    }

    override suspend fun addDailyEntry(userId: String, entry: Entry) {
        firestore.collection(USERS_COLLECTION)
            .document(userId)
            .collection(ENTRIES_COLLECTION)
            .document(entry.id)
            .set(entry)
    }

    override suspend fun updateDailyEntry(userId: String, entry: Entry) {
        firestore.collection(USERS_COLLECTION)
            .document(userId)
            .collection(ENTRIES_COLLECTION)
            .document(entry.id)
            .set(entry)
    }

    override suspend fun deleteDailyEntry(userId: String, entryId: String) {
        firestore.collection(USERS_COLLECTION)
            .document(userId)
            .collection(ENTRIES_COLLECTION)
            .document(entryId)
            .delete()
    }
}
