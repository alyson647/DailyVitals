package com.daily.vitals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import com.daily.vitals.AppInitializer.onApplicationStart
import com.daily.vitals.composeApp.database.DailyVitalsDatabase
import com.daily.vitals.database.DatabaseDriverFactory
import com.google.firebase.Firebase
import com.google.firebase.database.database

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        onApplicationStart()
        // THIS initializes the native FirebaseApp
        com.google.firebase.FirebaseApp.initializeApp(this)
        setContent {
            App()
        }
    }
}
