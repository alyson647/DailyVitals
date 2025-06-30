package com.daily.vitals

import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider


object AppInitializer {
    fun onApplicationStart() {
        GoogleAuthProvider.create(credentials = GoogleAuthCredentials(serverId = "1057388909480-8p4lrogr189j8cbruirophktregd05ch.apps.googleusercontent.com"))
    }
}