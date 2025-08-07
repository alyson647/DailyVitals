package com.daily.vitals

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.daily.vitals.theme.DailyVitalsTheme
import com.daily.vitals.ui.home.Home
import com.daily.vitals.ui.onboarding.FirstOnboardingScreen
import com.daily.vitals.ui.onboarding.SecondOnboardingScreen
import com.daily.vitals.ui.onboarding.ThirdOnboardingScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.KoinContext
import org.koin.core.context.startKoin

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
            NavHost(
                navController = rememberNavController(),
                startDestination = "home"
            ) {
                composable(route = "home") {
                    val viewModel = koinViewModel<MyViewModel>()
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = viewModel.getString()
                        )
                    }
                }
            }

        }
//        var currentScreen by remember { mutableStateOf(Screen.FirstOnboarding) }
//        var showSignInDialog by remember { mutableStateOf(false) }
//
//        var signedInName by remember { mutableStateOf("") }
//        var profileImage by remember { mutableStateOf("") }
//
//        Box(modifier = Modifier.fillMaxSize()) {
//            when (currentScreen) {
//                Screen.FirstOnboarding -> FirstOnboardingScreen(
//                    onSkipClick = { currentScreen = Screen.Home },
//                    onForwardClick = { currentScreen = Screen.SecondOnboarding }
//                )
//
//                Screen.SecondOnboarding -> SecondOnboardingScreen(
//                    onSkipClick = { currentScreen = Screen.Home },
//                    onForwardClick = { currentScreen = Screen.ThirdOnboarding },
//                    onBackClick = { currentScreen = Screen.FirstOnboarding }
//                )
//
//                Screen.ThirdOnboarding -> ThirdOnboardingScreen(
//                    onSkipClick = { showSignInDialog = true },
//                    onForwardClick = { showSignInDialog = true },
//                    onBackClick = { currentScreen = Screen.SecondOnboarding }
//                )
//
//                Screen.Home -> Home(signedInName, profileImage)
//
//                else -> {}
//            }
//
//            if (showSignInDialog) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.1f))
//                )
//
//                GoogleSignInDialog(
//                    onSkipClick = { _, _ ->
//                        showSignInDialog = false
//                        currentScreen = Screen.Home
//                    },
//                    onButtonClick = { displayName, profileUrl ->
//                        signedInName = displayName
//                        profileImage = profileUrl
//                        showSignInDialog = false
//                        currentScreen = Screen.Home
//                    }
//                )
//            }
//        }
    }
}
