package com.daily.vitals.feature.history.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.daily.vitals.design.components.GreenCheckIcon
import com.daily.vitals.design.components.RedCircleIcon
import com.daily.vitals.design.components.WarningIcon
import com.daily.vitals.design.theme.DailyVitalsTheme
import com.daily.vitals.domain.entry.model.Entry
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.borderline_fasting
import dailyvitals.composeapp.generated.resources.borderline_post_meal
import dailyvitals.composeapp.generated.resources.exercise
import dailyvitals.composeapp.generated.resources.fasting_ok
import dailyvitals.composeapp.generated.resources.greater_seven_hours
import dailyvitals.composeapp.generated.resources.high_fasting
import dailyvitals.composeapp.generated.resources.high_post_meal
import dailyvitals.composeapp.generated.resources.kg
import dailyvitals.composeapp.generated.resources.less_six_hours
import dailyvitals.composeapp.generated.resources.low_fasting
import dailyvitals.composeapp.generated.resources.low_post_meal
import dailyvitals.composeapp.generated.resources.mg_dl
import dailyvitals.composeapp.generated.resources.no
import dailyvitals.composeapp.generated.resources.no_data
import dailyvitals.composeapp.generated.resources.post_meal_ok
import dailyvitals.composeapp.generated.resources.six_seven_hours
import dailyvitals.composeapp.generated.resources.sleep
import dailyvitals.composeapp.generated.resources.weight
import dailyvitals.composeapp.generated.resources.yes
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HistoryRow(
    modifier: Modifier = Modifier,
    entry: Entry,
    day: Int
) {
    HistoryRowImpl(
        modifier = modifier,
        entry = entry,
        day = day
    )
}

@Composable
internal fun HistoryRowImpl(
    modifier: Modifier,
    entry: Entry,
    day: Int
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(MaterialTheme.colorScheme.surface)
            .padding(8.dp)
    ) {
        Row {
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(horizontal = 8.dp, vertical = 10.dp)
            ) {
                Text(
                    text = day.toString(),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (entry.fasting < 80) {
                            WarningIcon()
                        } else if (entry.fasting < 100) {
                            GreenCheckIcon()
                        } else if (entry.fasting < 125) {
                            WarningIcon()
                        } else {
                            RedCircleIcon()
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (entry.fasting < 80) {
                                    stringResource(Res.string.low_fasting)
                                } else if (entry.fasting < 100) {
                                    stringResource(Res.string.fasting_ok)
                                } else if (entry.fasting < 125) {
                                    stringResource(Res.string.borderline_fasting)
                                } else {
                                    stringResource(Res.string.high_fasting)
                                },
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                    Text(
                        text = entry.fasting.toString() + " " +  stringResource(Res.string.mg_dl),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (entry.postMeal < 90) {
                            WarningIcon()
                        } else if (entry.postMeal < 140) {
                            GreenCheckIcon()
                        } else if (entry.postMeal < 180) {
                            WarningIcon()
                        } else {
                            RedCircleIcon()
                        }
                        Text(
                            text = if (entry.postMeal < 90) {
                                stringResource(Res.string.low_post_meal)
                            } else if (entry.postMeal < 140) {
                                stringResource(Res.string.post_meal_ok)
                            } else if (entry.postMeal < 180) {
                                stringResource(Res.string.borderline_post_meal)
                            } else {
                                stringResource(Res.string.high_post_meal)
                            },
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                    Text(
                        text = entry.postMeal.toString() + " " + stringResource(Res.string.mg_dl),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }

        }
        Row(
            modifier = Modifier
                .padding(top = 12.dp, bottom = 4.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(Res.string.exercise) + " :",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelSmall,
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = if (entry.exercise) { stringResource(Res.string.yes) } else { stringResource(Res.string.no) },
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(MaterialTheme.colorScheme.outline)
                    .align(Alignment.CenterVertically)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(Res.string.weight) + " :",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelSmall,
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = if (entry.weight == 0f) {
                        stringResource(Res.string.no_data)
                    } else {
                        entry.weight.toString() + " " +  stringResource(Res.string.kg)
                    },
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(MaterialTheme.colorScheme.outline)
                    .align(Alignment.CenterVertically)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(Res.string.sleep) + " :",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelSmall,
                )
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = when (entry.sleep) {
                        0 -> stringResource(Res.string.less_six_hours)
                        1 -> stringResource(Res.string.six_seven_hours)
                        2 -> stringResource(Res.string.greater_seven_hours)
                        else -> stringResource(Res.string.no_data)
                    },
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Preview
@Composable
fun HistoryRowPreview() {
    DailyVitalsTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(24.dp)
            ) {
                HistoryRow(
                    modifier = Modifier,
                    entry = Entry(
                        id = "2024-12-24",
                        exercise = true,
                        fasting = 102,
                        postMeal = 90,
                        sleep = 2,
                        weight = 74.7f
                    ),
                    day = 24
                )
            }
        }
    }
}
