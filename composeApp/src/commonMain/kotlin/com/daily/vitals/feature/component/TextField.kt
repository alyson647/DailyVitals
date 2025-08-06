package com.daily.vitals.feature.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun PrimaryNumberField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {}
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        singleLine = true,
        textStyle = MaterialTheme.typography.titleLarge,
        placeholder = {
            Text(
                text = "0",
                color = MaterialTheme.colorScheme.surfaceVariant,
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = PrimaryNumberFieldColor().focusedContainerColor,
            unfocusedContainerColor = PrimaryNumberFieldColor().unfocusedContainerColor,
            disabledContainerColor = PrimaryNumberFieldColor().disabledContainerColor,
            focusedIndicatorColor = PrimaryNumberFieldColor().focusedIndicatorColor,
            unfocusedIndicatorColor = PrimaryNumberFieldColor().unfocusedIndicatorColor,
            disabledIndicatorColor = PrimaryNumberFieldColor().disabledIndicatorColor,
            focusedTextColor = PrimaryNumberFieldColor().focusedTextColor,
            unfocusedTextColor = PrimaryNumberFieldColor().unfocusedTextColor
        )
    )
}

@Composable
fun SecondaryNumberField(
    modifier: Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {}
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        singleLine = true,
        textStyle = MaterialTheme.typography.titleLarge,
        placeholder = {
            Text(
                text = "N/A",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = SecondaryNumberFieldColor().focusedContainerColor,
            unfocusedContainerColor = SecondaryNumberFieldColor().unfocusedContainerColor,
            disabledContainerColor = SecondaryNumberFieldColor().disabledContainerColor,
            focusedIndicatorColor = SecondaryNumberFieldColor().focusedIndicatorColor,
            unfocusedIndicatorColor = SecondaryNumberFieldColor().unfocusedIndicatorColor,
            disabledIndicatorColor = SecondaryNumberFieldColor().disabledIndicatorColor,
            focusedTextColor = SecondaryNumberFieldColor().focusedTextColor,
            unfocusedLabelColor = SecondaryNumberFieldColor().unfocusedTextColor
        )

    )

}


internal open class PrimaryNumberFieldColor() {
    open val focusedContainerColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.primary

    open val unfocusedContainerColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.primary

    open val disabledContainerColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.primary

    open val focusedIndicatorColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.onPrimary

    open val unfocusedIndicatorColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.onPrimary

    open val disabledIndicatorColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.onPrimary

    open val focusedTextColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.onPrimary

    open val unfocusedTextColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.onPrimary
}


internal open class SecondaryNumberFieldColor() {
    open val focusedContainerColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.surface

    open val unfocusedContainerColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.surface

    open val disabledContainerColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.surface

    open val focusedIndicatorColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.primary

    open val unfocusedIndicatorColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.primary

    open val disabledIndicatorColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.primary

    open val focusedTextColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.primary

    open val unfocusedTextColor: Color
        @Composable
        get() = MaterialTheme.colorScheme.primary
}
