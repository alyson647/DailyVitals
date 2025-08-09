package com.daily.vitals

import OnboardingDirections
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.daily.vitals.theme.DailyVitalsTheme
import com.daily.vitals.ui.home.Home
import com.daily.vitals.ui.onboarding.FirstOnboardingScreen
import com.daily.vitals.ui.onboarding.SecondOnboardingScreen
import com.daily.vitals.ui.onboarding.ThirdOnboardingScreen

@Composable
fun App() {
    DailyVitalsTheme {

        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Screen.FirstOnboarding.name
        ) {
            appGraph(
                modifier = Modifier.fillMaxSize(),
                navController = navController
            )
        }
    }
}

internal fun NavGraphBuilder.appGraph(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    composable(Screen.FirstOnboarding.name) {
        FirstOnboardingScreen(
            modifier = modifier
        ) { directions ->
            when (directions) {
                is OnboardingDirections.Next -> navController.navigate(Screen.SecondOnboarding.name)
                is OnboardingDirections.Home -> navController.navigate(Screen.Home.name)
                else -> Unit
            }
        }
    }
    composable(Screen.SecondOnboarding.name) {
        SecondOnboardingScreen(
            modifier = modifier
        ) { directions ->
            when (directions) {
                is OnboardingDirections.Next -> navController.navigate(Screen.ThirdOnboarding.name)
                is OnboardingDirections.Home -> navController.navigate(Screen.Home.name)
                is OnboardingDirections.Back -> navController.navigate(Screen.FirstOnboarding.name)
            }
        }
    }
    composable(Screen.ThirdOnboarding.name) {
        var showSignInDialog by remember { mutableStateOf(false) }

        // TODO: move this information to viewmodel or somewhere else
        var signedInName by remember { mutableStateOf("") }
        var profileImage by remember { mutableStateOf("") }

        ThirdOnboardingScreen(
            modifier = modifier
        ) { directions ->
            when (directions) {
                is OnboardingDirections.Next -> showSignInDialog = true
                is OnboardingDirections.Home -> navController.navigate(Screen.Home.name)
                is OnboardingDirections.Back -> navController.navigate(Screen.SecondOnboarding.name)
            }
        }
        if (showSignInDialog) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.1f))
            )

            // TODO: Add directions param for dialog
            GoogleSignInDialog(
                onSkipClick = { _, _ ->
                    showSignInDialog = false
                },
                onButtonClick = { displayName, profileUrl ->
                    // TODO: save this information somewhere
                    signedInName = displayName
                    profileImage = profileUrl
                    showSignInDialog = false
                    navController.navigate(Screen.Home.name) {
                        popUpTo(Screen.ThirdOnboarding.name) { inclusive = true }
                    }
                }
            )
        }

    }
    composable(Screen.Home.name) {
        Home("test name", "")
    }
}