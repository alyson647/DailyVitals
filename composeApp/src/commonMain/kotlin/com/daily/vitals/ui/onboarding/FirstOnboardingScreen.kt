package com.daily.vitals.ui.onboarding

import AppDirections
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

private var navigateTo: (AppDirections) -> Unit = {}

@Composable
internal fun FirstOnboardingScreen(
    modifier: Modifier = Modifier,
    directions: (AppDirections) -> Unit = {}
) {
    navigateTo = directions

    OnboardingScreen(
        modifier = modifier.statusBarsPadding(),
        heading = stringResource(Res.string.onboarding_heading_one),
        description = stringResource(Res.string.onboarding_description_one),
        image = Res.drawable.onboarding_one,
        forwardButtonImage = Res.drawable.arrow_right,
        backwardButtonImage = Res.drawable.arrow_left,
        progressFraction = 1f / 3f,
        onSkipClick = { navigateTo(AppDirections.Home) },
        onForwardClick = { navigateTo(AppDirections.Next) },
        onBackClick = null // no back button on first screen
    )
}
