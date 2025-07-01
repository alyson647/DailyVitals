package com.daily.vitals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.daily.vitals.AppInitializer.onApplicationStart
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider

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

// this works but is android specific
fun basicReadWrite() {
    // [START write_message]
    // Write a message to the database
    val database = Firebase.database
    val myRef = database.getReference("message")

    myRef.setValue("Hello, World - iOS!")
    // [END write_message]
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}