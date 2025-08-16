package com.daily.vitals.feature.home.component

import History
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.daily.vitals.domain.entry.model.Entry
import com.daily.vitals.feature.home.HomeViewModel
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.other_health_data
import org.jetbrains.compose.resources.stringResource

@Composable
fun OtherHealthData(
    entries: List<Entry>,
    userId: String,
    viewModel: HomeViewModel,
    currentEntry: Entry?,
    currentDate: String
) {
    OtherHealthDataImpl(
        entries = entries,
        userId = userId,
        viewModel = viewModel,
        currentEntry = currentEntry,
        currentDate = currentDate
    )
}

@Composable
private fun OtherHealthDataImpl(
    entries: List<Entry>,
    userId: String,
    viewModel: HomeViewModel,
    currentEntry: Entry?,
    currentDate: String
) {

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.other_health_data),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Sleep(
            selectedIndex = currentEntry?.sleep ?: -1,
            userId = userId,
            viewModel = viewModel
        )
        Spacer(modifier = Modifier.height(16.dp))
        ExerciseWeight(
            exercise = currentEntry?.exercise == true,
            weight = (currentEntry?.weight ?: "").toString(),
            userId = userId,
            viewModel = viewModel
        )
        Spacer(modifier = Modifier.height(16.dp))
        History(
            entries = entries
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}
