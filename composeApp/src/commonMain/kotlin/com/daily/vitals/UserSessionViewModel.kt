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

    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn: StateFlow<Boolean?> = _isLoggedIn

    private val _userId = MutableStateFlow("")
    val userId: StateFlow<String> = _userId

    init {
        viewModelScope.launch {
            prefs.data.collect { preferences ->
                _isLoggedIn.value = preferences[LOGGED_IN_KEY] == true
                _userId.value = preferences[USER_ID_KEY] ?: ""
            }
        }
    }

    fun setLoggedIn() {
        viewModelScope.launch {
            prefs.edit { preferences ->
                preferences[LOGGED_IN_KEY] = true
            }
            _isLoggedIn.value = true
        }
    }

    fun setUserId(userId: String) {
        if (userId == "") return
        viewModelScope.launch {
            prefs.edit { preferences ->
                preferences[USER_ID_KEY] = userId
            }
            _userId.value = userId
        }
    }

    companion object {
        private val LOGGED_IN_KEY = booleanPreferencesKey("logged_in")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
    }
}
