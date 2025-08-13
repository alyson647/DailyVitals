package com.daily.vitals.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daily.vitals.domain.entry.model.Entry
import com.daily.vitals.domain.entry.repository.DailyEntryRepository
import com.daily.vitals.domain.user.model.User
import com.daily.vitals.domain.user.repository.UserRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val user: User? = null,
    val entries: List<Entry> = emptyList()
)

class HomeViewModel(
    private val userRepository: UserRepository,
    private val entryRepository: DailyEntryRepository,
) : ViewModel() {

    private val _ui = MutableStateFlow(HomeUiState())
    val ui: StateFlow<HomeUiState> = _ui

    private var entriesJob: Job? = null
    private var userJob: Job? = null

    fun load(userId: String) {
        if (userId.isBlank()) {
            _ui.update { it.copy(isLoading = false, error = "Missing user id") }
            return
        }

        // cancel previous observers if any
        entriesJob?.cancel()
        userJob?.cancel()

        _ui.update { it.copy(isLoading = true, error = null) }

        // Observe user
        userJob = viewModelScope.launch {
            userRepository.getUserById(userId)
                .catch { e -> _ui.update { it.copy(error = e.message) } }
                .collect { user ->
                    _ui.update { it.copy(user = user) }
                }
        }

        // Observe daily entries
        entriesJob = viewModelScope.launch {
            entryRepository.getDailyEntriesByUser(userId)
                .catch { e -> _ui.update { it.copy(error = e.message, isLoading = false) } }
                .collect { list ->
                    _ui.update { it.copy(entries = list, isLoading = false) }
                }
        }
    }

    fun addOrUpdateEntry(userId: String, entry: Entry) = viewModelScope.launch {
        entryRepository.updateDailyEntry(userId, entry)
    }

    fun deleteEntry(userId: String, entryId: String) = viewModelScope.launch {
        entryRepository.deleteDailyEntry(userId, entryId)
    }
}
