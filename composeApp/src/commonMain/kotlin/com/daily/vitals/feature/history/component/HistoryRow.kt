package com.daily.vitals.feature.history.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.daily.vitals.design.components.CustomSwitch
import com.daily.vitals.design.components.GreenCheckIcon
import com.daily.vitals.design.components.RedCircleIcon
import com.daily.vitals.design.components.WarningIcon
import com.daily.vitals.design.theme.DailyVitalsTheme
import com.daily.vitals.design.theme.labelMediumSemiBold
import com.daily.vitals.domain.entry.model.Entry
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.borderline_fasting
import dailyvitals.composeapp.generated.resources.borderline_post_meal
import dailyvitals.composeapp.generated.resources.exercise
import dailyvitals.composeapp.generated.resources.fasting_ok
import dailyvitals.composeapp.generated.resources.high_fasting
import dailyvitals.composeapp.generated.resources.high_post_meal
import dailyvitals.composeapp.generated.resources.low_fasting
import dailyvitals.composeapp.generated.resources.low_post_meal
import dailyvitals.composeapp.generated.resources.mg_dl
import dailyvitals.composeapp.generated.resources.no_fasting_data
import dailyvitals.composeapp.generated.resources.no_post_meal_data
import dailyvitals.composeapp.generated.resources.post_meal_ok
import dailyvitals.composeapp.generated.resources.selected_icon
import dailyvitals.composeapp.generated.resources.sleep
import dailyvitals.composeapp.generated.resources.weight
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.toString

// TODO: create a class/enum/object to store information to be passed to composable

@Composable
fun HistoryRow(
    modifier: Modifier = Modifier,
    entry: Entry,
    fasting: Int,
    postMeal: Int
) {
    HistoryRowImpl(
        modifier = modifier,
        entry = entry,
        fasting = fasting,
        postMeal = postMeal
    )
}

@Composable
internal fun HistoryRowImpl(
    modifier: Modifier,
    entry: Entry,
    fasting: Int,
    postMeal: Int
) {
//    var date = LocalDate.parse(entry.id)
    // do column space between to even out space between date, fasting, and postmeal to fill up width?
    Column(
        modifier = modifier.fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = modifier.padding(vertical = 4.dp)
        ) {
            Column(
                modifier = modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(horizontal = 8.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "24",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (fasting == null) {
                        WarningIcon()
                    } else if (fasting < 80) {
                        WarningIcon()
                    } else if (fasting < 100) {
                        GreenCheckIcon()
                    } else if (fasting < 125) {
                        WarningIcon()
                    } else {
                        RedCircleIcon()
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (fasting == null) {
                            stringResource(Res.string.no_fasting_data)
                        } else if (fasting < 80) {
                            stringResource(Res.string.low_fasting)
                        } else if (fasting < 100) {
                            stringResource(Res.string.fasting_ok)
                        } else if (fasting < 125) {
                            stringResource(Res.string.borderline_fasting)
                        } else {
                            stringResource(Res.string.high_fasting)
                        },
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
                Text(
                    text = fasting.toString() + " " +  stringResource(Res.string.mg_dl),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (postMeal == null) {
                        WarningIcon()
                    } else if (postMeal < 90) {
                        WarningIcon()
                    } else if (postMeal < 140) {
                        GreenCheckIcon()
                    } else if (postMeal < 180) {
                        WarningIcon()
                    } else {
                        RedCircleIcon()
                    }
                    Text(
                        text = if (postMeal == null) {
                            stringResource(Res.string.no_post_meal_data)
                        } else if (postMeal < 90) {
                            stringResource(Res.string.low_post_meal)
                        } else if (postMeal < 140) {
                            stringResource(Res.string.post_meal_ok)
                        } else if (postMeal < 180) {
                            stringResource(Res.string.borderline_post_meal)
                        } else {
                            stringResource(Res.string.high_post_meal)
                        },
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
                Text(
                    text = postMeal.toString() + " " + stringResource(Res.string.mg_dl),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
        Row {
            Row(
                modifier = modifier.padding(start = 6.dp)
            ) {
                Text(
                    text = stringResource(Res.string.exercise) + " :",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelSmall,
                )
                CustomSwitch(
                    modifier = Modifier.size(width = 32.dp, height = 16.dp),
                    enabled = true,
                    checked = true,
                    thumbContent = {
//                        if (checked.value && enabled.value) {
//                            Image(
//                                modifier = Modifier.size(16.dp),
//                                painter = painterResource(Res.drawable.selected_icon),
//                                contentDescription = null,
//                                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.tertiary)
//                            )
//                        }
                        Image(
                            modifier = Modifier.size(12.dp),
                            painter = painterResource(Res.drawable.selected_icon),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.tertiary)
                        )
                    }
                )
            }
            Row {
                Text(
                    text = stringResource(Res.string.weight) + " :",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelSmall,
                )
            }
            Row {
                Text(
                    text = stringResource(Res.string.sleep) + " :",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelSmall,
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
            HistoryRow(
                modifier = Modifier,
                entry = Entry(
                    id = "2018-12-12",
                    exercise = true,
                    fasting = 130,
                    postMeal = 140,
                    sleep = 1,
                    weight = 74f
                ),
                fasting = 100,
                postMeal = 90
            )
        }
    }
}


