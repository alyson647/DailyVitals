package com.daily.vitals.domain.entry.usecase

import com.daily.vitals.domain.entry.model.Entry
import com.daily.vitals.domain.entry.repository.FirestoreEntryRepository

class UpdateEntryUseCase(
    private val firestoreEntryRepository: FirestoreEntryRepository
) {
    suspend operator fun invoke(userId: String, entry: Entry) {
        firestoreEntryRepository.updateEntry(userId = userId, entry = entry)
    }
}
