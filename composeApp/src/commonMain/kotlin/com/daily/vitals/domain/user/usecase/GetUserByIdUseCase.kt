package com.daily.vitals.domain.user.usecase

import com.daily.vitals.domain.user.repository.FirestoreUserRepository
import com.daily.vitals.domain.user.model.User
import kotlinx.coroutines.flow.Flow

class GetUserByIdUseCase(
    private val firestoreUserRepository: FirestoreUserRepository
) {
    operator fun invoke(userId: String): Flow<User?> {
        return firestoreUserRepository.getUserById(id = userId)
    }
}
