package com.daily.vitals.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
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
    onButtonClick: () -> Unit = {},
    progressFraction: Float,
) {
    OnboardingScreenImpl(
        modifier = modifier,
        heading = heading,
        description = description,
        image = image,
        buttonImage = buttonImage,
        onSkipClick = onSkipClick,
        onButtonClick = onButtonClick,
        progressFraction = progressFraction
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
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, bottom = 28.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = modifier.clickable {
                  onSkipClick()
                },
                text = stringResource(Res.string.skip),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Spacer(modifier = modifier.height(64.dp))
        OnboardingComponent(
            heading = heading,
            description = description,
            image = image
        )
        Spacer(modifier = modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomButton(
                buttonImage = buttonImage,
                onButtonClick = onButtonClick,
                progressFraction = progressFraction
            )
        }
    }
}

@Composable
private fun BottomButton(
    buttonImage: DrawableResource,
    progressFraction: Float, // 0f to 1f
    onButtonClick: () -> Unit
) {
    Box(
        modifier = Modifier.clickable {
        onButtonClick()
    },
        contentAlignment = Alignment.Center
    ) {
        // circular outline
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
            val radius = diameter / 2

            drawArc(
                color = Color.Black,
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
                .background(MaterialTheme.colorScheme.primary)
        )
        Image(
            modifier = Modifier.size(16.dp),
            painter = painterResource(buttonImage),
            contentDescription = null,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary)
        )
    }
}


