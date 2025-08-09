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
import org.koin.compose.KoinContext

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
        KoinContext {
            var currentScreen by remember { mutableStateOf(Screen.FirstOnboarding) }
            var showSignInDialog by remember { mutableStateOf(false) }

            // signed-in info
            var userId by remember { mutableStateOf("") }          // ⬅️ NEW
            var signedInName by remember { mutableStateOf("") }
            var profileImage by remember { mutableStateOf("") }

            Box(modifier = Modifier.fillMaxSize()) {
                when (currentScreen) {
                    Screen.FirstOnboarding -> FirstOnboardingScreen(
                        onSkipClick = { showSignInDialog = true },
                        onForwardClick = { currentScreen = Screen.SecondOnboarding }
                    )

                    Screen.SecondOnboarding -> SecondOnboardingScreen(
                        onSkipClick = { showSignInDialog = true },
                        onForwardClick = { currentScreen = Screen.ThirdOnboarding },
                        onBackClick = { currentScreen = Screen.FirstOnboarding }
                    )

                    Screen.ThirdOnboarding -> ThirdOnboardingScreen(
                        onSkipClick = { showSignInDialog = true },
                        onForwardClick = { showSignInDialog = true },
                        onBackClick = { currentScreen = Screen.SecondOnboarding }
                    )

                    Screen.Home -> Home(
                        userId = userId,
                        fallbackName = signedInName,
                        fallbackPhoto = profileImage
                    )

                    else -> {}
                }

                if (showSignInDialog) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.1f))
                    )

                    // NOTE: onButtonClick now returns uid too (see change below)
                    GoogleSignInDialog(
                        onSkipClick = { _, _ ->
                            // If skipping sign-in, keep userId empty; Home can guard against empty IDs.
                            showSignInDialog = false
                            currentScreen = Screen.Home
                        },
                        onButtonClick = { displayName, profileUrl, uid ->
                            signedInName = displayName
                            profileImage = profileUrl
                            userId = uid
                            showSignInDialog = false
                            currentScreen = Screen.Home
                        }
                    )
                }
            }
        }
    }
}
