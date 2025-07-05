package com.daily.vitals.ui.onboarding

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.google_sign_in
import dailyvitals.composeapp.generated.resources.onboarding_description_three
import dailyvitals.composeapp.generated.resources.onboarding_heading_three
import dailyvitals.composeapp.generated.resources.onboarding_three
import org.jetbrains.compose.resources.stringResource

@Composable
fun ThirdOnboardingScreen(
    onSkipClick: () -> Unit,
) {
    OnboardingScreen(
        modifier = Modifier.statusBarsPadding(),
        heading = stringResource(Res.string.onboarding_heading_three),
        description = stringResource(Res.string.onboarding_description_three),
        image = Res.drawable.onboarding_three,
        buttonImage = Res.drawable.google_sign_in,
        progressFraction = 1f,
        onSkipClick = onSkipClick,
        imageSizeDp = 48.dp
    )
}
