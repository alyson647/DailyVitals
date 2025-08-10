package com.daily.vitals.design.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dailyvitals.composeapp.generated.resources.Res
import dailyvitals.composeapp.generated.resources.green_check_icon
import dailyvitals.composeapp.generated.resources.muscle_icon
import dailyvitals.composeapp.generated.resources.red_circle_icon
import dailyvitals.composeapp.generated.resources.run_icon
import dailyvitals.composeapp.generated.resources.sleep_icon
import dailyvitals.composeapp.generated.resources.warning_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun WarningIcon() {
    Image(
        painter = painterResource(Res.drawable.warning_icon),
        contentDescription = "Warning icon",
        modifier = Modifier.size(20.dp)
    )
}

@Composable
fun SleepIcon() {
    Image(
        painter = painterResource(Res.drawable.sleep_icon),
        contentDescription = "Warning icon",
        modifier = Modifier.size(20.dp)
    )
}

@Composable
fun MuscleIcon() {
    Image(
        painter = painterResource(Res.drawable.muscle_icon),
        contentDescription = "Warning icon",
        modifier = Modifier.size(20.dp)
    )
}

@Composable
fun GreenCheckIcon() {
    Image(
        painter = painterResource(Res.drawable.green_check_icon),
        contentDescription = "Warning icon",
        modifier = Modifier.size(20.dp)
    )
}

@Composable
fun RedCircleIcon() {
    Image(
        painter = painterResource(Res.drawable.red_circle_icon),
        contentDescription = "Warning icon",
        modifier = Modifier.size(20.dp)
    )
}

@Composable
fun RunIcon() {
    Image(
        painter = painterResource(Res.drawable.run_icon),
        contentDescription = "Warning icon",
        modifier = Modifier.size(20.dp)
    )
}
