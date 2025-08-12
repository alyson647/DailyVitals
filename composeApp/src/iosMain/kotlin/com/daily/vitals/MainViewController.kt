package com.daily.vitals

import androidx.compose.ui.window.ComposeUIViewController
import com.daily.vitals.composeApp.database.DailyVitalsDatabase
import com.daily.vitals.database.DatabaseDriverFactory
import com.daily.vitals.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        // called once per initialization of this controller
        initKoin()
    }
) {
    App()
}
