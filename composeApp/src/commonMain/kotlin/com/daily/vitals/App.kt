package com.daily.vitals

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.daily.vitals.theme.DailyVitalsTheme
import dev.gitlive.firebase.Firebase
import org.jetbrains.compose.ui.tooling.preview.Preview
import dev.gitlive.firebase.database.*
import kotlinx.coroutines.launch

@Composable
@Preview
fun App() {
    DailyVitalsTheme {
        TestDatabaseCall()
//        GoogleSignIn()

    }
}

