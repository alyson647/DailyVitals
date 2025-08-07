package com.daily.vitals.di

import com.daily.vitals.MyViewModel
import com.daily.vitals.Platform
import com.daily.vitals.domain.user.repository.FirestoreUserRepository
import com.daily.vitals.domain.user.repository.UserRepository
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

// module is a container for a specific set of related dependencies
expect val platformModule: Module

val sharedModule = module {
//    single {
//        FirestoreUserRepository()
//    }.bind<UserRepository>()
    // OR
    singleOf(::FirestoreUserRepository).bind<UserRepository>()
    viewModelOf(::MyViewModel)
}