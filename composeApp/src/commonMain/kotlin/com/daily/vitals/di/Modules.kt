package com.daily.vitals.di

import com.daily.vitals.domain.user.repository.FirestoreUserRepository
import com.daily.vitals.domain.user.repository.UserRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single<FirebaseFirestore> { Firebase.firestore }
    singleOf(::FirestoreUserRepository).bind<UserRepository>()

    // TODO: add view models here in this format
    // example: viewModelOf(::MyViewModel)
}
