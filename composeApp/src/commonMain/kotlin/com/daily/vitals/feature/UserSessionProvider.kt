package com.daily.vitals.feature

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

class UserSessionProvider(
    private val prefs: DataStore<Preferences>
) {
    suspend fun isLocal(): Boolean {
        return prefs.data.first()[LOCAL_KEY] == true
    }

    suspend fun getUserId(): String {
        return prefs.data.first()[USER_ID_KEY] ?: ""
    }

    companion object {
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val LOCAL_KEY = booleanPreferencesKey("local")
    }
}