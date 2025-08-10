package com.daily.vitals.feature.onboarding

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.arrow_left
import dailyvitals.composeapp.generated.resources.arrow_right
import dailyvitals.composeapp.generated.resources.onboarding_description_one
import dailyvitals.composeapp.generated.resources.onboarding_heading_one
import dailyvitals.composeapp.generated.resources.onboarding_one
import org.jetbrains.compose.resources.stringResource

@Composable
fun FirstOnboardingScreen(
    onSkipClick: () -> Unit,
    onForwardClick: () -> Unit
) {
    OnboardingScreen(
        modifier = Modifier.statusBarsPadding(),
        heading = stringResource(Res.string.onboarding_heading_one),
        description = stringResource(Res.string.onboarding_description_one),
        image = Res.drawable.onboarding_one,
        forwardButtonImage = Res.drawable.arrow_right,
        backwardButtonImage = Res.drawable.arrow_left,
        progressFraction = 1f / 3f,
        onSkipClick = onSkipClick,
        onForwardClick = onForwardClick,
        onBackClick = null // no back button on first screen
    )
}
