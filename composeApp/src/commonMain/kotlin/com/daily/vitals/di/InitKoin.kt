package com.daily.vitals.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule, platformModule) // this allows us to extend the modules with
        // pure platform specific modules we might not have in our shared code
    }
}
