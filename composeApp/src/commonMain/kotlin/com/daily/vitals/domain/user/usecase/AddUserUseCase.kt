package com.daily.vitals.domain.user.usecase

import com.daily.vitals.domain.user.repository.FirestoreUserRepository
import com.daily.vitals.domain.user.model.User

class AddUserUseCase(
    private val firestoreUserRepository: FirestoreUserRepository
) {
    suspend operator fun invoke(user: User) {
        firestoreUserRepository.addUser(user = user)
    }
}
