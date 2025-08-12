package com.daily.vitals.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.daily.vitals.data.createDataStore
import app.cash.sqldelight.db.SqlDriver
import com.daily.vitals.composeApp.database.DailyVitalsDatabase
import com.daily.vitals.database.DatabaseDriverFactory
import org.koin.dsl.module

actual val platformModule = module {
    // Add Android-specific services here
    single<DataStore<Preferences>> {
        val context: Context = get()
        createDataStore(context)
    }

    single<DatabaseDriverFactory> { DatabaseDriverFactory(get()) }
    single<SqlDriver> { get<DatabaseDriverFactory>().createDriver() }
    single { DailyVitalsDatabase(get()) }
}
