package com.daily.vitals.feature.home

import AppDirections
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import org.koin.core.annotation.KoinExperimentalAPI
import androidx.compose.runtime.*
import com.daily.vitals.UserSessionViewModel
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
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

    val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toString()
    val testDate = "2025-08-09"

    LaunchedEffect(dataStoreUserId) {
        if (dataStoreUserId.isNotBlank()) {
            viewModel.load(dataStoreUserId)
        }
    }

    // Normal signed-in flow
    LaunchedEffect(Unit) {
        if (userId.isNotBlank()) {
            viewModel.load(userId)
        }
    }

    val ui by viewModel.ui.collectAsState()

    val currentEntry by viewModel.currentEntry.collectAsState()

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
            ui.isLoaded -> {
                val scrollState = rememberScrollState()
                HomeHeader(
                    name = ui.user?.name ?: "",
                    profileUrl = ui.user?.profilePicture ?: ""
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    Summary(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        currentDate = currentDate,
                        viewModel = viewModel,
                        userId = dataStoreUserId,
                        fasting = (currentEntry?.fasting ?: "").toString(),
                        postMeal = (currentEntry?.postMeal ?: "").toString()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OtherHealthData(
                        entries = ui.entries,
                        viewModel = viewModel,
                        userId = dataStoreUserId,
                        currentEntry = currentEntry,
                        currentDate = currentDate
                    )
                }
            }
            else -> Unit
        }
    }
}
