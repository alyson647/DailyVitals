package com.daily.vitals.domain.entry.usecase

import com.daily.vitals.domain.entry.model.Entry
import com.daily.vitals.domain.entry.repository.FirestoreEntryRepository
import kotlinx.coroutines.flow.Flow

class GetEntryByIdUseCase(
    private val firestoreEntryRepository: FirestoreEntryRepository
) {
    operator fun invoke(userId: String, entryId: String): Flow<Entry?> {
        return firestoreEntryRepository.getEntryById(userId = userId, entryId = entryId)
    }
}
