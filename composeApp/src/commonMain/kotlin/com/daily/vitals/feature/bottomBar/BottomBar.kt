package com.daily.vitals.feature.bottomBar

import Screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.history_icon
import dailyvitals.composeapp.generated.resources.home_icon
import dailyvitals.composeapp.generated.resources.notifications_icon
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomBar(navController: NavController) {
    val route = navController.currentBackStackEntryAsState().value?.destination?.route.orEmpty()

    val isHome = route == Screen.Home.name || route == "home/{userId}"
    val isHistory = route == Screen.History.name || route == "history/{userId}"
    val isNotif = route == Screen.Notifications.name || route == "notifications/{userId}"

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = RoundedCornerShape(28.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 3.dp,
            shadowElevation = 8.dp,
            modifier = Modifier
                .width(200.dp)
                .height(56.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterHorizontally)
            ) {
                TabIcon(
                    selected = isHome,
                    onClick = {
                        navController.navigate(Screen.Home.name) {
                            launchSingleTop = true; restoreState = true
                            popUpTo(Screen.Home.name) { saveState = true }
                        }
                    },
                    painterRes = Res.drawable.home_icon,
                    contentDescription = "Home"
                )
                TabIcon(
                    selected = isHistory,
                    onClick = {
                        navController.navigate(Screen.History.name) {
                            launchSingleTop = true; restoreState = true
                            popUpTo(Screen.Home.name) { saveState = true }
                        }
                    },
                    painterRes = Res.drawable.history_icon,
                    contentDescription = "History"
                )
                TabIcon(
                    selected = isNotif,
                    onClick = {
                        navController.navigate(Screen.Notifications.name) {
                            launchSingleTop = true; restoreState = true
                            popUpTo(Screen.Home.name) { saveState = true }
                        }
                    },
                    painterRes = Res.drawable.notifications_icon,
                    contentDescription = "Notifications"
                )
            }
        }
    }
}

@Composable
private fun TabIcon(
    selected: Boolean,
    onClick: () -> Unit,
    painterRes: DrawableResource,
    contentDescription: String
) {
    val interaction = remember { MutableInteractionSource() }

    val tint = if (selected) {
        MaterialTheme.colorScheme.onSurface
    } else {
        MaterialTheme.colorScheme.outlineVariant
    }

    Box(
        modifier = Modifier
            .size(24.dp)
            .clickable(
                interactionSource = interaction,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(painterRes),
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(tint)
        )
    }
}
