package com.daily.vitals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.daily.vitals.theme.DailyVitalsTheme
import com.daily.vitals.ui.home.Home
import com.daily.vitals.ui.onboarding.FirstOnboardingScreen
import com.daily.vitals.ui.onboarding.SecondOnboardingScreen
import com.daily.vitals.ui.onboarding.ThirdOnboardingScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class Screen {
    FirstOnboarding,
    SecondOnboarding,
    ThirdOnboarding,
    SignIn,
    Home
}

@Composable
@Preview
fun App() {
    DailyVitalsTheme {
        var currentScreen by remember { mutableStateOf(Screen.FirstOnboarding) }
        var showSignInDialog by remember { mutableStateOf(false) }

        Box(modifier = Modifier.fillMaxSize()) {
            when (currentScreen) {
                Screen.FirstOnboarding -> FirstOnboardingScreen(
                    onSkipClick = { currentScreen = Screen.Home },
                    onForwardClick = { currentScreen = Screen.SecondOnboarding }
                )

                Screen.SecondOnboarding -> SecondOnboardingScreen(
                    onSkipClick = { currentScreen = Screen.Home },
                    onForwardClick = { currentScreen = Screen.ThirdOnboarding },
                    onBackClick = { currentScreen = Screen.FirstOnboarding }
                )

                Screen.ThirdOnboarding -> ThirdOnboardingScreen(
                    onSkipClick = { showSignInDialog = true },
                    onForwardClick = { showSignInDialog = true },
                    onBackClick = { currentScreen = Screen.SecondOnboarding }
                )

                Screen.Home -> Home()

                else -> {}
            }

            if (showSignInDialog) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.1f))
                )

                GoogleSignInDialog(
                    onSkipClick = {
                        showSignInDialog = false
                        currentScreen = Screen.Home
                    },
                    onButtonClick = {
                        showSignInDialog = false
                        currentScreen = Screen.Home
                    }
                )
            }
        }
    }
}
