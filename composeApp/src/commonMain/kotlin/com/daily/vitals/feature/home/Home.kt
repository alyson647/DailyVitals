package com.daily.vitals.feature.home

import AppDirections
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daily.vitals.feature.home.component.HomeHeader
import com.daily.vitals.feature.home.component.OtherHealthData
import com.daily.vitals.feature.home.component.Summary
import com.daily.vitals.ui.home.HomeViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import androidx.compose.runtime.*
import com.daily.vitals.UserSessionViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(KoinExperimentalAPI::class)
@Composable
internal fun Home(
    modifier: Modifier = Modifier,
    userId: String = "",
    viewModel: HomeViewModel = koinViewModel(),
    userSessionViewModel: UserSessionViewModel = koinViewModel(),
    directions: (AppDirections) -> Unit = {},
) {
    val dataStoreUserId by userSessionViewModel.userId.collectAsState()

    LaunchedEffect(dataStoreUserId) {
        viewModel.load(dataStoreUserId)
    }
    // Normal signed-in flow
    LaunchedEffect(userId) {
        if (userId != "") {
            viewModel.load(userId)
        }
    }

    LaunchedEffect(Unit) {
        userSessionViewModel.setLoggedIn()
        userSessionViewModel.setUserId(userId)
    }
    val ui by viewModel.ui.collectAsState()

    // TODO: use directions for history screen once history screen created
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .statusBarsPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            ui.isLoading -> {
                Spacer(Modifier.height(32.dp)); CircularProgressIndicator()
            }
            else -> {
                HomeHeader(
                    name = ui.user?.name ?: "",
                    profileUrl = ui.user?.profilePicture ?: ""
                )
                Summary(modifier = Modifier.padding(horizontal = 16.dp))
                Spacer(modifier = Modifier.height(16.dp))
                OtherHealthData()
            }
        }
    }
}
