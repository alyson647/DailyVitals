package com.daily.vitals.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.did_you_sleep_well
import dailyvitals.composeapp.generated.resources.exercise
import dailyvitals.composeapp.generated.resources.greater_seven_hours
import dailyvitals.composeapp.generated.resources.last_five_days
import dailyvitals.composeapp.generated.resources.less_six_hours
import dailyvitals.composeapp.generated.resources.no_fasting_data
import dailyvitals.composeapp.generated.resources.other_health_data
import dailyvitals.composeapp.generated.resources.six_seven_hours
import dailyvitals.composeapp.generated.resources.sleep
import org.jetbrains.compose.resources.stringResource

@Composable
fun OtherHealthData() {
    OtherHealthDataImpl()
}

@Composable
private fun OtherHealthDataImpl() {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(Res.string.other_health_data),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.titleSmall,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Sleep()
        Spacer(modifier = Modifier.height(16.dp))
        ExerciseWeight()
        Spacer(modifier = Modifier.height(16.dp))
        History()
    }
}

@Composable
private fun Sleep() {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant), RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(Res.string.sleep),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelLarge,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = stringResource(Res.string.did_you_sleep_well),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outline), RoundedCornerShape(16.dp))
                .heightIn(min = 36.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = stringResource(Res.string.less_six_hours),
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                text = stringResource(Res.string.six_seven_hours),
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                text = stringResource(Res.string.greater_seven_hours),
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

@Composable
private fun ExerciseWeight() {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = stringResource(Res.string.exercise),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall,
            )
        }
        Column {
            Text(
                text = stringResource(Res.string.no_fasting_data),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}

@Composable
private fun History() {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(Res.string.last_five_days),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelLarge,
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

