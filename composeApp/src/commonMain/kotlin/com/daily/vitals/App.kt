package com.daily.vitals

import AppDirections
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.daily.vitals.design.theme.DailyVitalsTheme
import com.daily.vitals.feature.home.Home
import com.daily.vitals.feature.onboarding.FirstOnboardingScreen
import com.daily.vitals.feature.onboarding.component.GoogleSignInDialog
import com.daily.vitals.feature.onboarding.SecondOnboardingScreen
import com.daily.vitals.feature.onboarding.ThirdOnboardingScreen
import com.daily.vitals.ui.home.HomeViewModel
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun App() {
    DailyVitalsTheme {
        KoinContext {
            val navController = rememberNavController()
            val homeViewModel = koinViewModel<HomeViewModel>()

            // TODO: Issue 24 - check if the user is already logged in and if so, make
            //  the start destination Home and get user information
            NavHost(
                navController = navController,
                startDestination = Screen.FirstOnboarding.name
            ) {
                appGraph(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    homeViewModel = homeViewModel
                )
            }
        }
    }
}

internal fun NavGraphBuilder.appGraph(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeViewModel: HomeViewModel
) {
    composable(Screen.FirstOnboarding.name) {
        FirstOnboardingScreen(
            modifier = modifier
        ) { directions ->
            when (directions) {
                is AppDirections.Next -> navController.navigate(Screen.SecondOnboarding.name)
                is AppDirections.Home -> navController.navigate(Screen.Home.name)
                else -> Unit
            }
        }
    }
    composable(Screen.SecondOnboarding.name) {
        SecondOnboardingScreen(
            modifier = modifier
        ) { directions ->
            when (directions) {
                is AppDirections.Next -> navController.navigate(Screen.ThirdOnboarding.name)
                is AppDirections.Home -> navController.navigate(Screen.Home.name)
                is AppDirections.Back -> navController.navigate(Screen.FirstOnboarding.name)
            }
        }
    }
    composable(Screen.ThirdOnboarding.name) {
        var showSignInDialog by remember { mutableStateOf(false) }

        ThirdOnboardingScreen(
            modifier = modifier
        ) { directions ->
            when (directions) {
                is AppDirections.Next -> showSignInDialog = true
                is AppDirections.Home -> navController.navigate(Screen.Home.name)
                is AppDirections.Back -> navController.navigate(Screen.SecondOnboarding.name)
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
                homeViewModel = homeViewModel,
                onClose = { showSignInDialog = false },
                onButtonClick = {
                    showSignInDialog = false
                    navController.navigate(Screen.Home.name) {
                        popUpTo(Screen.ThirdOnboarding.name) { inclusive = true }
                    }
                }
            )
        }

    }
    composable(Screen.Home.name) {
        Home(
            modifier = modifier,
            homeViewModel = homeViewModel
        ) { directions ->
            when (directions) {
                else -> Unit
            }
        }
    }
}
