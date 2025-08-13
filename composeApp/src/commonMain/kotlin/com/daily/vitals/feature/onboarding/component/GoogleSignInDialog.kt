package com.daily.vitals.feature.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daily.vitals.UserSessionViewModel
import com.daily.vitals.domain.user.model.User
import com.daily.vitals.feature.onboarding.OnboardingViewModel
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.close_icon
import dailyvitals.composeapp.generated.resources.google_icon
import dailyvitals.composeapp.generated.resources.mobile_check
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun GoogleSignInDialog(
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {},
    onButtonClick: (String) -> Unit = {},
) {
    val userSessionViewModel: UserSessionViewModel = koinViewModel()
    val onboardingViewModel: OnboardingViewModel = koinViewModel()
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(width = 312.dp, height = 279.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top Row with icon centered, and close icon right-aligned
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Image(
                        painter = painterResource(Res.drawable.mobile_check),
                        contentDescription = "App Icon",
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.Center)
                    )
                    Image(
                        painter = painterResource(Res.drawable.close_icon),
                        contentDescription = "Close Dialog",
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.CenterEnd)
                            .padding(end = 10.dp)
                            .clickable { onClose.invoke() }
                    )
                }

                // Title
                Text(
                    text = "Daily Vitals",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Description
                Text(
                    text = "Your daily companion for tracking glucose, sleep, and movement — all in one simple place.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(start = 35.dp)
                )

                // Firebase Sign-In
                AuthUiHelperButtonsAndFirebaseAuth(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    onFirebaseResult = { result ->
                        val userId = result.getOrNull()?.uid ?: return@AuthUiHelperButtonsAndFirebaseAuth
                        val email = result.getOrNull()?.email ?: ""
                        val profilePicture = result.getOrNull()?.photoURL ?: ""
                        val name = result.getOrNull()?.displayName ?: ""
                        scope.launch {
                            userSessionViewModel.setUserId(userId)
                            userSessionViewModel.setIsLocal(false)
                            userSessionViewModel.setLoggedIn()

                            onboardingViewModel.addUser(
                                user = User(
                                    id = userId,
                                    email = email,
                                    profilePicture = profilePicture,
                                    name = name
                                )
                            )
                            onButtonClick.invoke(userId)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun AuthUiHelperButtonsAndFirebaseAuth(
    modifier: Modifier = Modifier,
    onFirebaseResult: (Result<FirebaseUser?>) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GoogleButtonUiContainerFirebase(onResult = onFirebaseResult, linkAccount = false) {
            Button(
                modifier = Modifier
                    .width(232.dp)
                    .height(40.dp),
                onClick = { this.onClick() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(50)
            ) {
                Image(
                    painter = painterResource(Res.drawable.google_icon),
                    contentDescription = "Google Logo",
                    modifier = Modifier.size(29.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Sign in with Google",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
