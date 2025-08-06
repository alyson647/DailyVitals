package com.daily.vitals.domain.entry.usecase

import com.daily.vitals.domain.entry.model.Entry
import com.daily.vitals.domain.entry.repository.FirestoreEntryRepository
import kotlinx.coroutines.flow.Flow

class GetEntriesByUserUseCase(
    private val firestoreEntryRepository: FirestoreEntryRepository
) {
    operator fun invoke(userId: String): Flow<List<Entry>> {
        return firestoreEntryRepository.getEntriesByUser(userId = userId)
    }
}
