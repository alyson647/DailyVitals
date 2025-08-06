package com.daily.vitals.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.daily.vitals.theme.titleMediumBold
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
    profileUrl: String
) {
    HomeHeaderImpl(
        name = name,
        profileUrl = profileUrl
    )
}

@Composable
private fun HomeHeaderImpl(
    name: String,
    profileUrl: String
) {
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
        if (profileUrl != "") {
            KamelImage(
                modifier =  Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = CircleShape
                    ),
                resource = asyncPainterResource(data = profileUrl),
                contentDescription = "profile image"
            )
        } else {
            Image(
                painter = painterResource(Res.drawable.generic_avatar),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = CircleShape
                    )
            )
        }
    }
}
