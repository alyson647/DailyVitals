package com.daily.vitals.domain.user.usecase

import com.daily.vitals.domain.user.model.User
import com.daily.vitals.domain.user.repository.FirestoreUserRepository

class DeleteUserUseCase(
    private val firestoreUserRepository: FirestoreUserRepository
) {
    suspend operator fun invoke(user: User) {
        firestoreUserRepository.deleteUser(user = user)
    }
}