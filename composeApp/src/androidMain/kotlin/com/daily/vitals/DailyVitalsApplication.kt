package com.daily.vitals

import android.app.Application
import com.daily.vitals.di.initKoin
import org.koin.android.ext.koin.androidContext

class DailyVitalsApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@DailyVitalsApplication) // adds the context only for android and not other platforms
        }
    }
}