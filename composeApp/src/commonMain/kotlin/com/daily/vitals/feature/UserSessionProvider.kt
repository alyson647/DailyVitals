package com.daily.vitals.feature

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import kotlinx.coroutines.flow.first

class UserSessionProvider(
    private val prefs: DataStore<Preferences>
) {
    suspend fun isLocal(): Boolean {
        return prefs.data.first()[LOCAL_KEY] == true
    }

    companion object {
        private val LOCAL_KEY = booleanPreferencesKey("local")
    }
}