package com.daily.vitals.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daily.vitals.ui.home.component.HomeHeader
import com.daily.vitals.ui.home.component.OtherHealthData
import com.daily.vitals.ui.home.component.Summary
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Home(
    userId: String?,
    fallbackName: String,
    fallbackPhoto: String,
    viewModel: HomeViewModel = koinViewModel()
) {
    // If no userId just render fallback UI
    if (userId.isNullOrBlank()) {
        BasicHomeContent(name = fallbackName, photo = fallbackPhoto)
        return
    }

    // Normal signed-in flow
    LaunchedEffect(userId) { viewModel.load(userId) }
    val ui by viewModel.ui.collectAsState()

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .statusBarsPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            ui.isLoading -> {
                Spacer(Modifier.height(32.dp)); CircularProgressIndicator()
            }
            ui.error != null -> {
                // You can show a soft message, or fall back to basic UI:
                BasicHomeContent(name = fallbackName, photo = fallbackPhoto)
            }
            else -> {
                HomeHeader(
                    name = ui.user?.name ?: fallbackName,
                    profileUrl = ui.user?.profilePicture ?: fallbackPhoto
                )
                Summary(modifier = Modifier.padding(horizontal = 16.dp))
                Spacer(Modifier.height(16.dp))
                OtherHealthData()
            }
        }
    }
}

@Composable
private fun BasicHomeContent(name: String, photo: String) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .statusBarsPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeHeader(name = name, profileUrl = photo)
        Summary(modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(16.dp))
        OtherHealthData()
    }
}
