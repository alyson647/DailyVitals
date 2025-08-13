package com.daily.vitals.di

import com.daily.vitals.UserSessionViewModel
import com.daily.vitals.domain.entry.repository.DailyEntryRepository
import com.daily.vitals.domain.user.repository.DynamicUserRepository
import com.daily.vitals.domain.entry.repository.FirestoreEntryRepository
import com.daily.vitals.domain.entry.repository.SqlDelightEntryRepository
import com.daily.vitals.domain.user.repository.FirestoreUserRepository
import com.daily.vitals.domain.user.repository.SqlDelightUserRepository
import com.daily.vitals.domain.user.repository.UserRepository
import com.daily.vitals.feature.UserSessionProvider
import com.daily.vitals.feature.onboarding.OnboardingViewModel
import com.daily.vitals.feature.home.HomeViewModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind

// Expect declaration for platform-specific module
// Each platform must provide an actual implementation
expect val platformModule: Module

// Shared Koin module for common dependencies across all platforms
val sharedModule = module {
    single<FirebaseFirestore> { Firebase.firestore }
    single<CoroutineDispatcher> { Dispatchers.Default }

    single<FirestoreUserRepository> { FirestoreUserRepository(get()) }
    single<SqlDelightUserRepository> { SqlDelightUserRepository(get(), get()) }

    single<DailyEntryRepository> { FirestoreEntryRepository(get()) }
    single<SqlDelightEntryRepository> { SqlDelightEntryRepository(get(), get()) }

    single { UserSessionProvider(get()) }

    singleOf(::DynamicUserRepository).bind<UserRepository>()

    viewModelOf(::HomeViewModel)
    viewModelOf(::UserSessionViewModel)
    viewModelOf(::OnboardingViewModel)
}
