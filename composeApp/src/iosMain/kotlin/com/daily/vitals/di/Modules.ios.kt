package com.daily.vitals.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.daily.vitals.data.createDataStore
import org.koin.dsl.module

actual val platformModule = module {
    // Can declare dependencies specific to iOS here
    single<DataStore<Preferences>> {
        createDataStore()
    }
}
