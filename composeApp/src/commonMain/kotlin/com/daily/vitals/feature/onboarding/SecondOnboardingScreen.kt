package com.daily.vitals.feature.onboarding

import AppDirections
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.daily.vitals.UserSessionViewModel
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.arrow_left
import dailyvitals.composeapp.generated.resources.arrow_right
import dailyvitals.composeapp.generated.resources.onboarding_description_two
import dailyvitals.composeapp.generated.resources.onboarding_heading_two
import dailyvitals.composeapp.generated.resources.onboarding_two
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

private var navigateTo: (AppDirections) -> Unit = {}

@OptIn(KoinExperimentalAPI::class)
@Composable
internal fun SecondOnboardingScreen(
    modifier: Modifier = Modifier,
    directions: (AppDirections) -> Unit = {},
) {
    navigateTo = directions

    val userSessionViewModel: UserSessionViewModel = koinViewModel()

    OnboardingScreen(
        modifier = modifier.statusBarsPadding(),
        heading = stringResource(Res.string.onboarding_heading_two),
        description = stringResource(Res.string.onboarding_description_two),
        image = Res.drawable.onboarding_two,
        forwardButtonImage = Res.drawable.arrow_right,
        backwardButtonImage = Res.drawable.arrow_left,
        progressFraction = 2f / 3f,
        onSkipClick = {
            userSessionViewModel.setLoggedIn()
            userSessionViewModel.setIsLocal(true)
            navigateTo(AppDirections.Home)
        },
        onForwardClick = { navigateTo(AppDirections.Next) },
        onBackClick = { navigateTo(AppDirections.Back) }
    )
}
