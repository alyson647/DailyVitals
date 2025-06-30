package com.daily.vitals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        GoogleAuthProvider.create(credentials = GoogleAuthCredentials(serverId = "1057388909480-8p4lrogr189j8cbruirophktregd05ch.apps.googleusercontent.com"))

        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}