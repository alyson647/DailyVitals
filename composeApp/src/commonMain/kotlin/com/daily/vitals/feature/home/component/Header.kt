package com.daily.vitals.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.daily.vitals.design.theme.titleMediumBold
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.generic_avatar
import dailyvitals.composeapp.generated.resources.good_morning
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeHeader(
    name: String,
    profileUrl: String,
    onSignOut: () -> Unit
) {
    HomeHeaderImpl(
        name = name,
        profileUrl = profileUrl,
        onSignOut = onSignOut
    )
}

@Composable
private fun HomeHeaderImpl(
    name: String,
    profileUrl: String,
    onSignOut: () -> Unit
) {
    var menuExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        Column {
            Text(
                text = stringResource(Res.string.good_morning),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = name,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMediumBold,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        val avatarModifier = Modifier
            .size(44.dp)
            .clip(CircleShape)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onPrimary,
                shape = CircleShape
            )
            .clickable { menuExpanded = true }

        Column {
            if (profileUrl.isNotBlank()) {
                KamelImage(
                    modifier = avatarModifier,
                    resource = asyncPainterResource(data = profileUrl),
                    contentDescription = "profile image"
                )
            } else {
                Image(
                    painter = painterResource(Res.drawable.generic_avatar),
                    contentDescription = "Profile picture",
                    modifier = avatarModifier
                )
            }

            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Sign out") },
                    onClick = {
                        menuExpanded = false
                        onSignOut()
                    }
                )
            }
        }
    }
}
