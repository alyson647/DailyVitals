package com.daily.vitals.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun OnboardingComponent(
    modifier: Modifier = Modifier,
    heading: String,
    description: String,
    image: DrawableResource
) {
    OnboardingComponentImpl(
        modifier = modifier,
        heading = heading,
        description = description,
        image = image
    )
}

@Composable
private fun OnboardingComponentImpl(
    modifier: Modifier,
    heading: String,
    description: String,
    image: DrawableResource
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OnboardingImage(
            modifier = modifier,
            image = image
        )
        Spacer(modifier = modifier.height(57.dp))
        Text(
            text = heading,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.height(16.dp))
        Text(
            text = description,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun OnboardingImage(
    modifier: Modifier = Modifier,
    image: DrawableResource
) {
    Box(
        modifier = modifier
            .size(320.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceContainerLow),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(303.dp),
            painter = painterResource(image),
            contentDescription = null
        )
    }
}
