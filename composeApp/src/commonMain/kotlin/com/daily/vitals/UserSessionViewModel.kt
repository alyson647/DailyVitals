package com.daily.vitals

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserSessionViewModel(
    private val prefs: DataStore<Preferences>
): ViewModel() {

    private val _showOnboarding = MutableStateFlow<Boolean?>(null)
    val showOnboarding: StateFlow<Boolean?> = _showOnboarding

    private val _userId = MutableStateFlow("")
    val userId: StateFlow<String> = _userId

    init {
        viewModelScope.launch {
            prefs.data.collect { preferences ->
                _showOnboarding.value = preferences[SHOW_ONBOARDING_KEY] != false
                _userId.value = preferences[USER_ID_KEY] ?: ""
            }
        }
    }

    suspend fun setShowOnboardingFalse() {
        prefs.edit { preferences ->
            preferences[SHOW_ONBOARDING_KEY] = false
        }
        _showOnboarding.value = false
    }

    suspend fun setUserId(userId: String) {
        if (userId == "") return
        prefs.edit { preferences ->
            preferences[USER_ID_KEY] = userId
        }
        _userId.value = userId
    }

    suspend fun setIsLocal(value: Boolean) {
        prefs.edit { preferences ->
            preferences[LOCAL_KEY] = value
        }
    }

    suspend fun clearSession() {
        prefs.edit { preferences ->
            preferences.remove(USER_ID_KEY)
            preferences[SHOW_ONBOARDING_KEY] = true
        }
        _userId.value = ""
        _showOnboarding.value = true
    }

    companion object {
        private val SHOW_ONBOARDING_KEY = booleanPreferencesKey("show_onboarding")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val LOCAL_KEY = booleanPreferencesKey("local")
    }
}
