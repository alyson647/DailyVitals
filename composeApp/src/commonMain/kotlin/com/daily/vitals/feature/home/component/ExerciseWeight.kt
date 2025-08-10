package com.daily.vitals.feature.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.daily.vitals.design.theme.labelLargeSemiBold
import com.daily.vitals.design.theme.titleLargeBold
import com.daily.vitals.design.components.CustomSwitch
import com.daily.vitals.design.components.MuscleIcon
import com.daily.vitals.design.components.RunIcon
import com.daily.vitals.design.components.SecondaryNumberField
import com.daily.vitals.design.components.WarningIcon
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.add_weight
import dailyvitals.composeapp.generated.resources.exercise
import dailyvitals.composeapp.generated.resources.go_for_a_run
import dailyvitals.composeapp.generated.resources.kg
import dailyvitals.composeapp.generated.resources.nice_job
import dailyvitals.composeapp.generated.resources.selected_icon
import dailyvitals.composeapp.generated.resources.steady_weight
import dailyvitals.composeapp.generated.resources.weight
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ExerciseWeight() {

    val checked = remember { mutableStateOf(true) }
    val enabled = remember { mutableStateOf(true) }
    var weightText by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Top)
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(Res.string.exercise),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelLargeSemiBold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            CustomSwitch(
                enabled = enabled.value,
                checked = checked.value,
                onCheckedChange = {
                    checked.value = !checked.value
                },
                thumbContent = {
                    if (checked.value && enabled.value) {
                        Image(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(Res.drawable.selected_icon),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.tertiary)
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row {
                if (checked.value) {
                    RunIcon()
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(Res.string.nice_job),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                } else {
                    WarningIcon()
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(Res.string.go_for_a_run),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .background(MaterialTheme.colorScheme.outline)
                .align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .align(Alignment.Top)
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(Res.string.weight),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelLargeSemiBold,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                SecondaryNumberField(
                    modifier = Modifier
                        .widthIn(max = 80.dp),
                    value = weightText,
                    onValueChange = {
                        // allows decimals and five or less digits
                        if (it.matches(Regex("^\\d*\\.?\\d*\$")) && it.filter { c -> c.isDigit() }.length <= 5) {
                            weightText = it
                        }
                    }
                )
                Text(
                    text = stringResource(Res.string.kg),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.titleLargeBold,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row {
                MuscleIcon()
                Spacer(modifier = Modifier.width(4.dp))
                if (weightText == "") {
                    Text(
                        text = stringResource(Res.string.add_weight),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                } else {
                    Text(
                        text = stringResource(Res.string.steady_weight),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}
