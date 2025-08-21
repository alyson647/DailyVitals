package com.daily.vitals.feature.onboarding

import androidx.lifecycle.ViewModel
import com.daily.vitals.domain.user.model.User
import com.daily.vitals.domain.user.repository.UserRepository

class OnboardingViewModel(
    private val userRepository: UserRepository
): ViewModel() {

    suspend fun addUser(user: User) {
        userRepository.addUser(user)
    }
}