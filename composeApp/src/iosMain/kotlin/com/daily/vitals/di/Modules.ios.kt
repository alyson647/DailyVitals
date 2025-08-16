package com.daily.vitals.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.daily.vitals.data.createDataStore
import app.cash.sqldelight.db.SqlDriver
import com.daily.vitals.composeApp.database.DailyVitalsDatabase
import com.daily.vitals.database.DatabaseDriverFactory
import org.koin.dsl.module

actual val platformModule = module {
    // Can declare dependencies specific to iOS here
    single<DataStore<Preferences>> {
        createDataStore()
    }
    single<DatabaseDriverFactory> { DatabaseDriverFactory() }
    single<SqlDriver> { get<DatabaseDriverFactory>().createDriver() }
    single { DailyVitalsDatabase(get()) }
}
