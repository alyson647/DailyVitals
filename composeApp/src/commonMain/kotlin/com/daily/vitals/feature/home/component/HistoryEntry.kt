package com.daily.vitals.feature.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.daily.vitals.theme.labelLargeSemiBold
import com.daily.vitals.theme.labelMediumSemiBold
import com.daily.vitals.ui.component.GreenCheckIcon
import com.daily.vitals.ui.component.RedCircleIcon
import com.daily.vitals.ui.component.WarningIcon
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.borderline_fasting
import dailyvitals.composeapp.generated.resources.borderline_post_meal
import dailyvitals.composeapp.generated.resources.fasting_ok
import dailyvitals.composeapp.generated.resources.high_fasting
import dailyvitals.composeapp.generated.resources.high_post_meal
import dailyvitals.composeapp.generated.resources.low_fasting
import dailyvitals.composeapp.generated.resources.low_post_meal
import dailyvitals.composeapp.generated.resources.mg_dl
import dailyvitals.composeapp.generated.resources.no_fasting_data
import dailyvitals.composeapp.generated.resources.no_post_meal_data
import dailyvitals.composeapp.generated.resources.post_meal_ok
import org.jetbrains.compose.resources.stringResource

@Composable
fun HistoryEntry(
    date: String?,
    fasting: Int?,
    postMeal: Int?
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
    ) {
        // individual history row
        Spacer(modifier = Modifier.height(8.dp))

        if (date != null) {
            Text(
                text = date,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelLargeSemiBold,
                fontStyle = FontStyle.Italic
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
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
                        style = MaterialTheme.typography.labelMediumSemiBold,
                    )

                }
                Text(
                    text = fasting.toString() + " " +  stringResource(Res.string.mg_dl),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelMediumSemiBold,
                )
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
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
                        style = MaterialTheme.typography.labelMediumSemiBold,
                    )
                }
                Text(
                    text = postMeal.toString() + " " + stringResource(Res.string.mg_dl),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.labelMediumSemiBold,
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )

    }
}
