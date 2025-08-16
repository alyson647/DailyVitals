package com.daily.vitals.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.daily.vitals.composeApp.database.DailyVitalsDatabase

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = DailyVitalsDatabase.Schema,
            context = context,
            name = "DailyVitalsDatabase.db"
        )
    }
}
