package com.daily.vitals.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
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
    forwardButtonImage: DrawableResource,
    backwardButtonImage: DrawableResource,
    onSkipClick: () -> Unit = {},
    onForwardClick: () -> Unit = onSkipClick,
    onBackClick: (() -> Unit)? = null,
    progressFraction: Float,
    imageSizeDp: Dp = 16.dp
) {
    OnboardingScreenImpl(
        modifier = modifier,
        heading = heading,
        description = description,
        image = image,
        forwardButtonImage = forwardButtonImage,
        backwardButtonImage = backwardButtonImage,
        onSkipClick = onSkipClick,
        onForwardClick = onForwardClick,
        onBackClick = onBackClick,
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
    forwardButtonImage: DrawableResource,
    backwardButtonImage: DrawableResource,
    onSkipClick: () -> Unit,
    onForwardClick: () -> Unit,
    onBackClick: (() -> Unit)? = null,
    progressFraction: Float,
    imageSizeDp: Dp
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 32.dp, end = 32.dp, bottom = 28.dp)
    ) {
        // Top left backward button and top right skip button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 22.dp)
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (onBackClick != null) {
                Icon(
                    modifier = Modifier
                        .clickable { onBackClick.invoke() },
                    painter = painterResource(backwardButtonImage),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
            Text(
                modifier = Modifier
                    .clickable { onSkipClick() },
                text = stringResource(Res.string.skip),
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.titleSmall
            )
        }

        // Center content section including image, heading, and description
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

        // forward button at the bottom
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            ArcButton(
                buttonImage = forwardButtonImage,
                progressFraction = progressFraction,
                onClick = onForwardClick,
                imageSizeDp = imageSizeDp
            )
        }
    }
}

@Composable
private fun ArcButton(
    buttonImage: DrawableResource,
    progressFraction: Float,
    onClick: () -> Unit,
    imageSizeDp: Dp = 16.dp
) {
    val progressColor = MaterialTheme.colorScheme.primary

    Box(
        modifier = Modifier.clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        // Circular outer border
        Box(
            modifier = Modifier
                .size(70.dp)
                .border(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.inversePrimary,
                    shape = CircleShape
                )
        )

        // Arc progress ring around the button
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

        // Inner circular button with image icon
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(imageSizeDp),
                painter = painterResource(buttonImage),
                contentDescription = null
            )
        }
    }
}
