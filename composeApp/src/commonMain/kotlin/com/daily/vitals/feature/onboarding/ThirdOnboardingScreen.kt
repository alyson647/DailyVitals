package com.daily.vitals.feature.onboarding

import AppDirections
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daily.vitals.UserSessionViewModel
import com.daily.vitals.domain.user.model.User
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.arrow_left
import dailyvitals.composeapp.generated.resources.google_sign_in
import dailyvitals.composeapp.generated.resources.onboarding_description_three
import dailyvitals.composeapp.generated.resources.onboarding_heading_three
import dailyvitals.composeapp.generated.resources.onboarding_three
import kotlinx.coroutines.launch
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
    val onboardingViewModel: OnboardingViewModel = koinViewModel()
    val scope = rememberCoroutineScope()

    OnboardingScreen(
        modifier = modifier.statusBarsPadding(),
        heading = stringResource(Res.string.onboarding_heading_three),
        description = stringResource(Res.string.onboarding_description_three),
        image = Res.drawable.onboarding_three,
        forwardButtonImage = Res.drawable.google_sign_in,
        backwardButtonImage = Res.drawable.arrow_left,
        progressFraction = 1f,
        onSkipClick = {
            scope.launch {
                userSessionViewModel.setUserId("1")
                userSessionViewModel.setIsLocal(true)
                userSessionViewModel.setShowOnboardingFalse()

                onboardingViewModel.addUser(
                    user = User(
                        id = "1",
                        email = "",
                        profilePicture = "",
                        name = "Guest"
                    )
                )
                navigateTo(AppDirections.Home)
            }
        },
        onForwardClick = { navigateTo(AppDirections.Next) },
        onBackClick = { navigateTo(AppDirections.Back) },
        imageSizeDp = 48.dp
    )
}
