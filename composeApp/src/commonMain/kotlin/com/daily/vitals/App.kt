package com.daily.vitals

import androidx.compose.runtime.Composable
import com.daily.vitals.theme.DailyVitalsTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    DailyVitalsTheme {
        GoogleSignIn()
    }
}
