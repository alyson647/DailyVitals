package com.daily.vitals.feature.onboarding

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.arrow_left
import dailyvitals.composeapp.generated.resources.arrow_right
import dailyvitals.composeapp.generated.resources.onboarding_description_two
import dailyvitals.composeapp.generated.resources.onboarding_heading_two
import dailyvitals.composeapp.generated.resources.onboarding_two
import org.jetbrains.compose.resources.stringResource

@Composable
fun SecondOnboardingScreen(
    onSkipClick: () -> Unit,
    onForwardClick: () -> Unit,
    onBackClick: () -> Unit
) {
    OnboardingScreen(
        modifier = Modifier.statusBarsPadding(),
        heading = stringResource(Res.string.onboarding_heading_two),
        description = stringResource(Res.string.onboarding_description_two),
        image = Res.drawable.onboarding_two,
        forwardButtonImage = Res.drawable.arrow_right,
        backwardButtonImage = Res.drawable.arrow_left,
        progressFraction = 2f / 3f,
        onSkipClick = onSkipClick,
        onForwardClick = onForwardClick,
        onBackClick = onBackClick
    )
}
