package com.daily.vitals.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.daily.vitals.composeApp.database.DailyVitalsDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = DailyVitalsDatabase.Schema,
            name = "DailyVitalsDatabase.db"
        )
    }
}