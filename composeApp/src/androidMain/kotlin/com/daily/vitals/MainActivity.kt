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
            val database = DailyVitalsDatabase(DatabaseDriverFactory(LocalContext.current).createDriver())
            App(database)
        }

    }
}

// this works but is android specific
fun basicReadWrite() {
    // [START write_message]
    // Write a message to the database
    val database = Firebase.database
    val myRef = database.getReference("message")

    myRef.setValue("Hello, World - iOS!")
    // [END write_message]
}
