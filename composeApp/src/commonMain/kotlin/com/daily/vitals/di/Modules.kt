package com.daily.vitals.di

import com.daily.vitals.domain.entry.repository.DailyEntryRepository
import com.daily.vitals.domain.entry.repository.FirestoreEntryRepository
import com.daily.vitals.domain.user.repository.FirestoreUserRepository
import com.daily.vitals.domain.user.repository.UserRepository
import com.daily.vitals.ui.home.HomeViewModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.compose.viewmodel.dsl.viewModelOf

// Expect declaration for platform-specific module
// Each platform must provide an actual implementation
expect val platformModule: Module

// Shared Koin module for common dependencies across all platforms
val sharedModule = module {
    single<FirebaseFirestore> { Firebase.firestore }
    singleOf(::FirestoreUserRepository).bind<UserRepository>()
    singleOf(::FirestoreEntryRepository).bind<DailyEntryRepository>()

    singleOf(::HomeViewModel)
}
