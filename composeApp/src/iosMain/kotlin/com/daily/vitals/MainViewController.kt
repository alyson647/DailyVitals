package com.daily.vitals

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.daily.vitals.data.createDataStore
import com.daily.vitals.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        // called once per initialization of this controller
        initKoin()
    }
) {
    App(
        prefs = remember {
            createDataStore()
        }
    )
}
