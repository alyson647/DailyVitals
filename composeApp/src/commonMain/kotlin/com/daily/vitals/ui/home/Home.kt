package com.daily.vitals.ui.home

import AppDirections
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daily.vitals.ui.home.component.HomeHeader
import com.daily.vitals.ui.home.component.OtherHealthData
import com.daily.vitals.ui.home.component.Summary


@Composable
internal fun Home(
    modifier: Modifier = Modifier,
    directions: (AppDirections) -> Unit = {}
) {
    // TODO: get name, profile url information
    // TODO: use directions for history screen once history screen created
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .statusBarsPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeHeader(
            name = "Buddy", // TODO: update with user name
            profileUrl = "" // TODO: update with user profile image
        )
        Summary(modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        OtherHealthData()
    }
}
