package com.daily.vitals.domain.user.usecase

import com.daily.vitals.domain.user.repository.FirestoreUserRepository
import com.daily.vitals.domain.user.model.User
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase(
    private val firestoreUserRepository: FirestoreUserRepository
) {
    operator fun invoke(): Flow<List<User>> {
        return firestoreUserRepository.getUsers()
    }
}
