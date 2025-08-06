package com.daily.vitals.feature.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonColors
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.daily.vitals.theme.labelLargeSemiBold
import com.daily.vitals.feature.component.SleepIcon
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.did_you_sleep_well
import dailyvitals.composeapp.generated.resources.greater_seven_hours
import dailyvitals.composeapp.generated.resources.less_six_hours
import dailyvitals.composeapp.generated.resources.moderate_sleep
import dailyvitals.composeapp.generated.resources.poor_sleep
import dailyvitals.composeapp.generated.resources.six_seven_hours
import dailyvitals.composeapp.generated.resources.sleep
import dailyvitals.composeapp.generated.resources.sound_sleep
import org.jetbrains.compose.resources.stringResource

@Composable
fun Sleep() {
    var selectedIndex by remember { mutableIntStateOf(-1) }
    val options = listOf(
        stringResource(Res.string.less_six_hours),
        stringResource(Res.string.six_seven_hours),
        stringResource(Res.string.greater_seven_hours)
    )

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(Res.string.sleep),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelLargeSemiBold,
            )
            Spacer(modifier = Modifier.weight(1f))
            SleepIcon()
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = when (selectedIndex) {
                    0 -> stringResource(Res.string.poor_sleep)
                    1 -> stringResource(Res.string.moderate_sleep)
                    2 -> stringResource(Res.string.sound_sleep)
                    else -> stringResource(Res.string.did_you_sleep_well)
                },
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = 0,
                    count = options.size
                ),
                onClick = { selectedIndex = 0 },
                selected = 0 == selectedIndex,
                label = {
                    Text(
                        text = stringResource(Res.string.less_six_hours),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.labelLarge,
                    )
                },
                border = SegmentedButtonDefaults.borderStroke(
                    color = MaterialTheme.colorScheme.outline
                ),
                colors = SegmentedButtonColors(
                    activeContainerColor = MaterialTheme.colorScheme.errorContainer,
                    activeContentColor = MaterialTheme.colorScheme.error,
                    activeBorderColor = MaterialTheme.colorScheme.outline,
                    inactiveContainerColor = Color.Transparent,
                    inactiveContentColor = MaterialTheme.colorScheme.onSurface,
                    inactiveBorderColor = MaterialTheme.colorScheme.outline,
                    disabledActiveContainerColor = Color.Transparent,
                    disabledActiveContentColor = MaterialTheme.colorScheme.onSurface,
                    disabledActiveBorderColor = MaterialTheme.colorScheme.outline,
                    disabledInactiveContainerColor = Color.Transparent,
                    disabledInactiveContentColor = MaterialTheme.colorScheme.onSurface,
                    disabledInactiveBorderColor = MaterialTheme.colorScheme.outline
                )
            )
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = 1,
                    count = options.size
                ),
                onClick = { selectedIndex = 1 },
                selected = 1 == selectedIndex,
                label = {
                    Text(
                        text = stringResource(Res.string.six_seven_hours),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.labelLarge,
                    )
                },
                border = SegmentedButtonDefaults.borderStroke(
                    color = MaterialTheme.colorScheme.outline
                ),
                colors = SegmentedButtonColors(
                    activeContainerColor = MaterialTheme.colorScheme.onPrimary,
                    activeContentColor = MaterialTheme.colorScheme.onSurface,
                    activeBorderColor = MaterialTheme.colorScheme.outline,
                    inactiveContainerColor = Color.Transparent,
                    inactiveContentColor = MaterialTheme.colorScheme.onSurface,
                    inactiveBorderColor = MaterialTheme.colorScheme.outline,
                    disabledActiveContainerColor = Color.Transparent,
                    disabledActiveContentColor = MaterialTheme.colorScheme.onSurface,
                    disabledActiveBorderColor = MaterialTheme.colorScheme.outline,
                    disabledInactiveContainerColor = Color.Transparent,
                    disabledInactiveContentColor = MaterialTheme.colorScheme.onSurface,
                    disabledInactiveBorderColor = MaterialTheme.colorScheme.outline
                )
            )
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = 2,
                    count = options.size
                ),
                onClick = { selectedIndex = 2 },
                selected = 2 == selectedIndex,
                label = {
                    Text(
                        text = stringResource(Res.string.greater_seven_hours),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.labelLarge,
                    )
                },
                border = SegmentedButtonDefaults.borderStroke(
                    color = MaterialTheme.colorScheme.outline
                ),
                colors = SegmentedButtonColors(
                    activeContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    activeContentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    activeBorderColor = MaterialTheme.colorScheme.outline,
                    inactiveContainerColor = Color.Transparent,
                    inactiveContentColor = MaterialTheme.colorScheme.onSurface,
                    inactiveBorderColor = MaterialTheme.colorScheme.outline,
                    disabledActiveContainerColor = Color.Transparent,
                    disabledActiveContentColor = MaterialTheme.colorScheme.onSurface,
                    disabledActiveBorderColor = MaterialTheme.colorScheme.outline,
                    disabledInactiveContainerColor = Color.Transparent,
                    disabledInactiveContentColor = MaterialTheme.colorScheme.onSurface,
                    disabledInactiveBorderColor = MaterialTheme.colorScheme.outline
                )
            )
        }
    }
}
