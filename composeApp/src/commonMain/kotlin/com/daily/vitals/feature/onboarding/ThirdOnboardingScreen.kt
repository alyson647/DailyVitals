package com.daily.vitals.feature.onboarding

import AppDirections
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daily.vitals.UserSessionViewModel
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.arrow_left
import dailyvitals.composeapp.generated.resources.google_sign_in
import dailyvitals.composeapp.generated.resources.onboarding_description_three
import dailyvitals.composeapp.generated.resources.onboarding_heading_three
import dailyvitals.composeapp.generated.resources.onboarding_three
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

private var navigateTo: (AppDirections) -> Unit = {}

@OptIn(KoinExperimentalAPI::class)
@Composable
internal fun ThirdOnboardingScreen(
    modifier: Modifier = Modifier,
    directions: (AppDirections) -> Unit = {},
) {
    navigateTo = directions

    val userSessionViewModel: UserSessionViewModel = koinViewModel()

    OnboardingScreen(
        modifier = modifier.statusBarsPadding(),
        heading = stringResource(Res.string.onboarding_heading_three),
        description = stringResource(Res.string.onboarding_description_three),
        image = Res.drawable.onboarding_three,
        forwardButtonImage = Res.drawable.google_sign_in,
        backwardButtonImage = Res.drawable.arrow_left,
        progressFraction = 1f,
        onSkipClick = {
            userSessionViewModel.setLoggedIn()
            userSessionViewModel.setIsLocal(true)
            navigateTo(AppDirections.Home)
        },
        onForwardClick = { navigateTo(AppDirections.Next) },
        onBackClick = { navigateTo(AppDirections.Back) },
        imageSizeDp = 48.dp
    )
}
