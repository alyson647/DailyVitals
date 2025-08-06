package com.daily.vitals.domain.entry.usecase

import com.daily.vitals.domain.entry.repository.FirestoreEntryRepository

class DeleteEntryUseCase(
    private val firestoreEntryRepository: FirestoreEntryRepository
) {
    suspend operator fun invoke(userId: String, entryId: String) {
        firestoreEntryRepository.deleteEntry(userId = userId, entryId = entryId)
    }
}
