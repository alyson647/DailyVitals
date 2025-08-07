package com.daily.vitals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daily.vitals.domain.user.model.User
import com.daily.vitals.domain.user.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MyViewModel(
    private val repository: UserRepository
): ViewModel() {


    fun getString(): String {
        return repository.getString()
    }

}