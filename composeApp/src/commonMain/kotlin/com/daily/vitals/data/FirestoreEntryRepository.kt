package com.daily.vitals.data

import com.daily.vitals.model.ENTRIES_COLLECTION
import com.daily.vitals.model.Entry
import com.daily.vitals.model.USERS_COLLECTION
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.flow

class FirestoreEntryRepository : EntryRepository {

    private val firestore = Firebase.firestore

    override fun getEntriesByUser(userId: String) = flow {
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

    override fun getEntryById(userId: String, entryId: String) = flow {
        firestore.collection(USERS_COLLECTION)
            .document(userId)
            .collection(ENTRIES_COLLECTION)
            .document(entryId)
            .snapshots
            .collect { documentSnapshot ->
                emit(documentSnapshot.data<Entry>())
            }
    }

    override suspend fun addEntry(userId: String, entry: Entry) {
        firestore.collection(USERS_COLLECTION)
            .document(userId)
            .collection(ENTRIES_COLLECTION)
            .document(entry.id)
            .set(entry)
    }

    override suspend fun updateEntry(userId: String, entry: Entry) {
        firestore.collection(USERS_COLLECTION)
            .document(userId)
            .collection(ENTRIES_COLLECTION)
            .document(entry.id)
            .set(entry)
    }

    override suspend fun deleteEntry(userId: String, entryId: String) {
        firestore.collection(USERS_COLLECTION)
            .document(userId)
            .collection(ENTRIES_COLLECTION)
            .document(entryId)
            .delete()
    }
}
