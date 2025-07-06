package com.daily.vitals.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val Color = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    inversePrimary = InversePrimary,
    secondary = Secondary,
    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,
    tertiary = Tertiary,
    onTertiaryContainer = OnTertiaryContainer,
    surface = Surface,
    onSurface = OnSurface,
    onSurfaceVariant = OnSurfaceVariant,
    surfaceContainerLow = SurfaceContainerLow,
    error = Error,
    errorContainer = ErrorContainer,
    outline = Outline,
    outlineVariant = OutlineVariant
)

@Composable
fun DailyVitalsTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = Color,
        typography = Typography,
        content = content
    )
}