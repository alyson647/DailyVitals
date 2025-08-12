package com.daily.vitals.di

import app.cash.sqldelight.db.SqlDriver
import com.daily.vitals.composeApp.database.DailyVitalsDatabase
import com.daily.vitals.database.DatabaseDriverFactory
import org.koin.dsl.module

actual val platformModule = module {
    // Can declare dependencies specific to ios here

    single<DatabaseDriverFactory> { DatabaseDriverFactory() }
    single<SqlDriver> { get<DatabaseDriverFactory>().createDriver() }
    single { DailyVitalsDatabase(get()) }
}
