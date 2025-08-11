package com.daily.vitals

import AppDirections
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
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
import kotlinx.coroutines.launch
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import kotlinx.coroutines.flow.map

@OptIn(KoinExperimentalAPI::class)
@Composable
fun App(
    prefs: DataStore<Preferences>
) {
    DailyVitalsTheme {
        KoinContext {
            val isLoggedIn by prefs
                .data
                .map {
                    val loggedInKey = booleanPreferencesKey("logged_in")
                    it[loggedInKey] == true
                }
                .collectAsState(null)

            val navController = rememberNavController()
            val homeViewModel = koinViewModel<HomeViewModel>()

            // TODO: Issue 24 - check if the user is already logged in and if so, make
            //  the start destination Home and get user information
            if (isLoggedIn != null) {
                NavHost(
                    navController = navController,
                    startDestination = if (isLoggedIn == true) {
                        Screen.Home.name
                    } else {
                        Screen.FirstOnboarding.name
                    }
                ) {
                    appGraph(
                        modifier = Modifier.fillMaxSize(),
                        navController = navController,
                        homeViewModel = homeViewModel,
                        prefs = prefs
                    )
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

        }
    }
}

internal fun NavGraphBuilder.appGraph(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeViewModel: HomeViewModel,
    prefs: DataStore<Preferences>
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

        val scope = rememberCoroutineScope()

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
                onButtonClick = { userId ->
                    // TODO: move logic to view model
                    scope.launch {
                        prefs.edit { dataStore ->
                            val loggedInKey = booleanPreferencesKey("logged_in")
                            val userIdKey = stringPreferencesKey("user_id")
                            dataStore[loggedInKey] = true
                            if (!userId.isEmpty()) {
                                dataStore[userIdKey] = userId
                            }
                        }
                    }
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
