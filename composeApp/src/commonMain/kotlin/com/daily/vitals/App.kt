package com.daily.vitals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.daily.vitals.design.theme.DailyVitalsTheme
import com.daily.vitals.feature.onboarding.FirstOnboardingScreen
import com.daily.vitals.feature.onboarding.component.GoogleSignInDialog
import com.daily.vitals.feature.onboarding.SecondOnboardingScreen
import com.daily.vitals.feature.onboarding.ThirdOnboardingScreen
import com.daily.vitals.ui.home.Home
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

enum class Screen {
    FirstOnboarding,
    SecondOnboarding,
    ThirdOnboarding,
    SignIn,
    Home
}

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    DailyVitalsTheme {
        KoinContext {
            val userSessionViewModel = koinViewModel<UserSessionViewModel>()
            val isLoggedIn by userSessionViewModel.isLoggedIn.collectAsState()
            var currentScreen by remember { mutableStateOf<Screen?>(null) }

            LaunchedEffect(isLoggedIn) {
                if (currentScreen == null && isLoggedIn != null) {
                    currentScreen = if (isLoggedIn == true) Screen.Home else Screen.FirstOnboarding
                }
            }

            var showSignInDialog by remember { mutableStateOf(false) }

            // signed-in info
            var userId by remember { mutableStateOf("") }
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
                            userSessionViewModel.setLoggedIn()
                            userSessionViewModel.setUserId(uid)
                        }
                    )
                }
            }
        }
    }
}
