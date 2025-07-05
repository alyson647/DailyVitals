package com.daily.vitals.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.daily.vitals.ui.onboarding.component.OnboardingComponent
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.skip
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    heading: String,
    description: String,
    image: DrawableResource,
    buttonImage: DrawableResource,
    onSkipClick: () -> Unit = {},
    onButtonClick: () -> Unit = onSkipClick, // Default to onSkipClick
    progressFraction: Float,
    imageSizeDp: Dp = 16.dp
) {
    OnboardingScreenImpl(
        modifier = modifier,
        heading = heading,
        description = description,
        image = image,
        buttonImage = buttonImage,
        onSkipClick = onSkipClick,
        onButtonClick = onButtonClick,
        progressFraction = progressFraction,
        imageSizeDp = imageSizeDp
    )
}

@Composable
private fun OnboardingScreenImpl(
    modifier: Modifier,
    heading: String,
    description: String,
    image: DrawableResource,
    buttonImage: DrawableResource,
    onSkipClick: () -> Unit,
    onButtonClick: () -> Unit,
    progressFraction: Float,
    imageSizeDp: Dp
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, bottom = 28.dp)
    ) {
        // Skip Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp)
                .align(Alignment.TopEnd),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.clickable { onSkipClick() },
                text = stringResource(Res.string.skip),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.titleSmall
            )
        }

        // Centered Content
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OnboardingComponent(
                heading = heading,
                description = description,
                image = image
            )
        }

        // Bottom Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomButton(
                buttonImage = buttonImage,
                onButtonClick = onButtonClick,
                progressFraction = progressFraction,
                imageSizeDp = imageSizeDp
            )
        }
    }
}

@Composable
private fun BottomButton(
    buttonImage: DrawableResource,
    progressFraction: Float,
    onButtonClick: () -> Unit,
    imageSizeDp: Dp = 16.dp
) {
    val progressColor = MaterialTheme.colorScheme.primary

    Box(
        modifier = Modifier.clickable { onButtonClick() },
        contentAlignment = Alignment.Center
    ) {
        // Circular outline
        Box(
            modifier = Modifier
                .size(70.dp)
                .border(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.inversePrimary,
                    shape = CircleShape
                )
        )
        androidx.compose.foundation.Canvas(modifier = Modifier.size(70.dp)) {
            val strokeWidth = 3.dp.toPx()
            val diameter = size.minDimension

            drawArc(
                color = progressColor,
                startAngle = 270f,
                sweepAngle = 360f * progressFraction,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                size = Size(diameter, diameter),
                topLeft = Offset.Zero
            )
        }
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(imageSizeDp), // ✅ Use param size
                painter = painterResource(buttonImage),
                contentDescription = null,
            )
        }
    }
}
