package com.daily.vitals.feature.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CustomSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    enabled: Boolean = true,
    onCheckedChange: ((Boolean) -> Unit) = {},
    thumbContent: (@Composable () -> Unit)? = null,
) {
    CustomSwitchImpl(
        modifier = modifier,
        checked = checked,
        enabled = enabled,
        onCheckedChange = onCheckedChange,
        thumbContent = thumbContent
    )
}

@Composable
private fun CustomSwitchImpl(
    modifier: Modifier,
    checked: Boolean,
    enabled: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
    thumbContent: (@Composable () -> Unit)? = null,
) {
    Switch(
        modifier = modifier,
        checked = checked,
        enabled = enabled,
        onCheckedChange = onCheckedChange,
        thumbContent = thumbContent,
        colors = SwitchColors(
            checkedThumbColor = SwitchStyle().checkedThumbColor,
            checkedTrackColor = SwitchStyle().checkedTrackColor,
            checkedBorderColor = SwitchStyle().checkedBorderColor,
            checkedIconColor = SwitchStyle().checkedIconColor,
            uncheckedThumbColor = SwitchStyle().uncheckedThumbColor,
            uncheckedTrackColor = SwitchStyle().uncheckedTrackColor,
            uncheckedBorderColor = SwitchStyle().uncheckedBorderColor,
            uncheckedIconColor = SwitchStyle().uncheckedIconColor,
            disabledCheckedThumbColor = SwitchStyle().disabledCheckedThumbColor,
            disabledCheckedTrackColor = SwitchStyle().disabledCheckedTrackColor,
            disabledCheckedBorderColor = SwitchStyle().disabledUncheckedBorderColor,
            disabledCheckedIconColor = SwitchStyle().disabledCheckedIconColor,
            disabledUncheckedThumbColor = SwitchStyle().disabledUncheckedThumbColor,
            disabledUncheckedTrackColor = SwitchStyle().disabledUncheckedTrackColor,
            disabledUncheckedBorderColor = SwitchStyle().disabledUncheckedBorderColor,
            disabledUncheckedIconColor = SwitchStyle().disabledUncheckedIconColor
        )

    )
}

internal open class SwitchStyle() {
    // checked and enabled
    open val checkedThumbColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.onPrimary

    open val checkedTrackColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.tertiary

    open val checkedBorderColor: Color
        @Composable
        get() = Color.Transparent

    open val checkedIconColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.tertiary

    // unchecked and enabled
    open val uncheckedThumbColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.error

    open val uncheckedTrackColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.errorContainer

    open val uncheckedBorderColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.error

    open val uncheckedIconColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.onPrimary

    // checked and disabled
    open val disabledCheckedThumbColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.outlineVariant

    open val disabledCheckedTrackColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.outline

    open val disabledCheckedBorderColor: Color
        @Composable
        get() = Color.Transparent

    open val disabledCheckedIconColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.outlineVariant

    // unchecked and disabled
    open val disabledUncheckedThumbColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.outlineVariant

    open val disabledUncheckedTrackColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.outline

    open val disabledUncheckedBorderColor: Color
        @Composable
        get() = Color.Transparent

    open val disabledUncheckedIconColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.outlineVariant
}
