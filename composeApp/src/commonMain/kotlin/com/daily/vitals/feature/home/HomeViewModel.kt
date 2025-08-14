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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class HomeUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val user: User? = null,
    val entries: List<Entry> = emptyList()
) {
    val isLoaded: Boolean
        get() = !isLoading && user != null
}

class HomeViewModel(
    private val userRepository: UserRepository,
    private val entryRepository: DailyEntryRepository,
) : ViewModel() {

    private val _ui = MutableStateFlow(HomeUiState())
    val ui: StateFlow<HomeUiState> = _ui

    private val _currentEntry = MutableStateFlow<Entry?>(null)
    val currentEntry: StateFlow<Entry?> = _currentEntry.asStateFlow()

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

        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toString()

        // Observe daily entries
        entriesJob = viewModelScope.launch {
            entryRepository.getDailyEntriesByUser(userId)
                .catch { e -> _ui.update { it.copy(error = e.message, isLoading = false) } }
                .collect { list ->
                    _ui.update { it.copy(entries = list, isLoading = false) }
                    val todayEntry = list.find { it.id == currentDate }
                    if (todayEntry == null) {
                        val newEntry = Entry(id = currentDate)
                        entryRepository.addDailyEntry(userId, newEntry)
                        _currentEntry.value = newEntry
                    } else {
                        _currentEntry.value = todayEntry
                    }
                }
        }
    }

    fun updateFastingValue(userId: String, fasting: Int) {
        viewModelScope.launch {
            _currentEntry.value?.let { currentEntry ->
                val updatedEntry = currentEntry.copy(fasting = fasting)
                _currentEntry.value = updatedEntry
                entryRepository.updateDailyEntry(userId = userId, entry = updatedEntry)
            }
        }
    }

    fun updatePostMealValue(userId: String, postMeal: Int) {
        viewModelScope.launch {
            _currentEntry.value?.let { currentEntry ->
                val updatedEntry = currentEntry.copy(postMeal = postMeal)
                _currentEntry.value = updatedEntry
                entryRepository.updateDailyEntry(userId = userId, entry = updatedEntry)
            }
        }
    }

    fun updateSleepValue(userId: String, sleep: Int) {
        viewModelScope.launch {
            _currentEntry.value?.let { currentEntry ->
                val updatedEntry = currentEntry.copy(sleep = sleep)
                _currentEntry.value = updatedEntry
                entryRepository.updateDailyEntry(userId = userId, entry = updatedEntry)
            }
        }
    }

    fun updateExerciseValue(userId: String, exercise: Boolean) {
        viewModelScope.launch {
            _currentEntry.value?.let { currentEntry ->
                val updatedEntry = currentEntry.copy(exercise = exercise)
                _currentEntry.value = updatedEntry
                entryRepository.updateDailyEntry(userId = userId, entry = updatedEntry)
            }
        }
    }

    fun updateWeightValue(userId: String, weight: Float) {
        viewModelScope.launch {
            _currentEntry.value?.let { currentEntry ->
                val updatedEntry = currentEntry.copy(weight = weight)
                _currentEntry.value = updatedEntry
                entryRepository.updateDailyEntry(userId = userId, entry = updatedEntry)
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
