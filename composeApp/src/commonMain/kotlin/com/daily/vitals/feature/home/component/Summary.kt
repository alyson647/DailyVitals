package com.daily.vitals.feature.home.component

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.daily.vitals.design.theme.labelLargeSemiBold
import com.daily.vitals.design.theme.titleLargeBold
import com.daily.vitals.design.components.GreenCheckIcon
import com.daily.vitals.design.components.PrimaryNumberField
import com.daily.vitals.design.components.RedCircleIcon
import com.daily.vitals.design.components.WarningIcon
import com.daily.vitals.feature.home.HomeViewModel
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.borderline_fasting
import dailyvitals.composeapp.generated.resources.borderline_post_meal
import dailyvitals.composeapp.generated.resources.fasting
import dailyvitals.composeapp.generated.resources.fasting_ok
import dailyvitals.composeapp.generated.resources.high_fasting
import dailyvitals.composeapp.generated.resources.high_post_meal
import dailyvitals.composeapp.generated.resources.info_icon
import dailyvitals.composeapp.generated.resources.low_fasting
import dailyvitals.composeapp.generated.resources.low_post_meal
import dailyvitals.composeapp.generated.resources.mg_dl
import dailyvitals.composeapp.generated.resources.no_fasting_data
import dailyvitals.composeapp.generated.resources.no_post_meal_data
import dailyvitals.composeapp.generated.resources.post_meal_ok
import dailyvitals.composeapp.generated.resources.today_summary
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Summary(
    modifier: Modifier = Modifier,
    currentDate: String,
    userId: String,
    viewModel: HomeViewModel,
    fasting: String = "",
    postMeal: String = ""
) {
    SummaryImpl(
        modifier = modifier,
        currentDate = currentDate,
        userId = userId,
        viewModel = viewModel,
        fasting = fasting,
        postMeal = postMeal
    )
}

@Composable
fun SummaryImpl(
    modifier: Modifier,
    currentDate: String,
    userId: String,
    viewModel: HomeViewModel,
    fasting: String,
    postMeal: String
) {
    var fastingText by remember { mutableStateOf(fasting) }
    var postMealText by remember { mutableStateOf(postMeal) }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.inversePrimary), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        // today's summary and info icon
        Box {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(Res.string.today_summary),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(Res.drawable.info_icon),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // fasting data and post meal data summary
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (fastingText == "") {
                WarningIcon()
            } else if (fastingText.toInt() < 80) {
                WarningIcon()
            } else if (fastingText.toInt() < 100) {
                GreenCheckIcon()
            } else if (fastingText.toInt() < 125) {
                WarningIcon()
            } else {
                RedCircleIcon()
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = if (fastingText == "") {
                    stringResource(Res.string.no_fasting_data)
                } else if (fastingText.toInt() < 80) {
                    stringResource(Res.string.low_fasting)
                } else if (fastingText.toInt() < 100) {
                    stringResource(Res.string.fasting_ok)
                } else if (fastingText.toInt() < 125) {
                    stringResource(Res.string.borderline_fasting)
                } else {
                    stringResource(Res.string.high_fasting)
                },
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.labelLargeSemiBold,
            )
            if (postMealText == "") {
                WarningIcon()
            } else if (postMealText.toInt() < 90) {
                WarningIcon()
            } else if (postMealText.toInt() < 140) {
                GreenCheckIcon()
            } else if (postMealText.toInt() < 180) {
                WarningIcon()
            } else {
                RedCircleIcon()
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                modifier = Modifier.weight(1f),
                text =
                    if (postMealText == "") {
                        stringResource(Res.string.no_post_meal_data)
                    } else if (postMealText.toInt() < 90) {
                        stringResource(Res.string.low_post_meal)
                    } else if (postMealText.toInt() < 140) {
                        stringResource(Res.string.post_meal_ok)
                    } else if (postMealText.toInt() < 180) {
                        stringResource(Res.string.borderline_post_meal)
                    } else {
                        stringResource(Res.string.high_post_meal)
                    },
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.labelLargeSemiBold,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        // fasting post meal section
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outline), RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(Res.string.fasting),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelLargeSemiBold,
                    textAlign = TextAlign.Center
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    PrimaryNumberField(
                        modifier = Modifier
                            .widthIn(max = 80.dp),
                        value = fastingText,
                        onValueChange = {
                            // allow no decimals
                            if (it.all { char -> char.isDigit() }  && it.length <= 3) {
                                fastingText = it
                                if (fastingText.isNotBlank()) {
                                    viewModel.updateFastingValue(userId = userId, fasting = fastingText.toInt())
                                }
                            }
                        }
                    )
                    Text(
                        text = stringResource(Res.string.mg_dl),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLargeBold,
                        textAlign = TextAlign.Center
                    )
                }

            }
            Spacer(modifier = Modifier.width(16.dp))

            // Use IntrinsicSize to limit the height of the Row to its content
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .align(Alignment.CenterVertically) // Critical: aligns with tallest content
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(Res.string.no_fasting_data),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelLargeSemiBold,
                    textAlign = TextAlign.Center
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    PrimaryNumberField(
                        modifier = Modifier
                            .widthIn(max = 80.dp),
                        value = postMealText,
                        onValueChange = {
                            // allow no decimals
                            if (it.all { char -> char.isDigit() } && it.length <= 3) {
                                postMealText = it
                                if (postMealText.isNotBlank()) {
                                    viewModel.updatePostMealValue(userId = userId, postMeal = postMealText.toInt())
                                }
                            }
                        }
                    )
                    Text(
                        text = stringResource(Res.string.mg_dl),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLargeBold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
