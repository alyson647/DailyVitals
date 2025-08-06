package com.daily.vitals.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import com.daily.vitals.Platform
import com.daily.vitals.domain.user.usecase.GetUserByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val platform: Platform,
    private val getUserByIdUseCase: GetUserByIdUseCase
) : ViewModel() {

    private val _greeting = MutableStateFlow("")
    val greeting = _greeting.asStateFlow()

    private fun fetchPlatformGreeting() {
    }

}