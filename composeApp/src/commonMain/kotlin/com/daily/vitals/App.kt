package com.daily.vitals

import AppDirections
import Screen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.daily.vitals.design.theme.DailyVitalsTheme
import com.daily.vitals.feature.bottomBar.BottomBar
import com.daily.vitals.feature.history.HistoryScreen
import com.daily.vitals.feature.home.Home
import com.daily.vitals.feature.notifications.NotificationScreen
import com.daily.vitals.feature.onboarding.FirstOnboardingScreen
import com.daily.vitals.feature.onboarding.component.GoogleSignInDialog
import com.daily.vitals.feature.onboarding.SecondOnboardingScreen
import com.daily.vitals.feature.onboarding.ThirdOnboardingScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun App() {
    DailyVitalsTheme {
        val navController = rememberNavController()
        val userSessionViewModel = koinViewModel<UserSessionViewModel>()
        val showOnboarding by userSessionViewModel.showOnboarding.collectAsState()
        var startDestination  = if (showOnboarding == false) Screen.Home.name else Screen.FirstOnboarding.name
      
        LaunchedEffect(isLoggedIn) {
            if (startDestination == null && isLoggedIn != null) {
                startDestination = if (isLoggedIn == true)
                    Screen.Home.name
                else
                    Screen.FirstOnboarding.name
            }
        }

        startDestination?.let { start ->
            Box(Modifier.fillMaxSize()) {
                // Screen content
                NavHost(
                    navController = navController,
                    startDestination = start,
                    modifier = Modifier.fillMaxSize()
                ) {
                    appGraph(modifier = Modifier.fillMaxSize(), navController = navController)
                }

                val route = navController.currentBackStackEntryAsState()
                    .value?.destination?.route.orEmpty()

                val showBar = route in setOf(
                    Screen.Home.name, "home/{userId}",
                    Screen.History.name, "history/{userId}",
                    Screen.Notifications.name, "notifications/{userId}"
                )

                if (showBar) {
                    Box(
                        Modifier
                            .align(Alignment.BottomCenter)
                            .zIndex(1f)
                    ) {
                        BottomBar(navController)
                    }
                }
            }
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
                is AppDirections.Next -> navController.navigate(Screen.SecondOnboarding.name)
                is AppDirections.Home ->navController.navigate(Screen.Home.name)
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

            GoogleSignInDialog(
                onClose = { showSignInDialog = false },
                onButtonClick = { userId ->
                    showSignInDialog = false
                    navController.navigate("home/$userId")
                }
            )
        }

    }

    composable(Screen.Home.name) {
        Home(
            modifier = modifier
        ) { directions ->
            when (directions) {
                else -> Unit
            }
        }
    }

    composable(
        route = "home/{userId}",
        arguments = listOf(navArgument("userId") { type = NavType.StringType })
    ) { backStackEntry ->
        val userId = backStackEntry.arguments?.getString("userId") ?: ""

        Home(
            modifier = modifier,
            userId = userId
        ) { directions ->
            when (directions) {
                else -> Unit
            }
        }
    }

    composable(Screen.History.name) { HistoryScreen() }

    composable(Screen.Notifications.name) { NotificationScreen() }
}
