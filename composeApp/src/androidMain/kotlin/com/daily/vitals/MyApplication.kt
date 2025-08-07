package com.daily.vitals

import android.app.Application
import com.daily.vitals.di.initKoin
import org.koin.android.ext.koin.androidContext

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MyApplication) // adds the context only for android and not other platforms
        }
    }
}