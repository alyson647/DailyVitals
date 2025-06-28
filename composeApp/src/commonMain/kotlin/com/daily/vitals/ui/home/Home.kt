package com.daily.vitals.ui.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Home() {
    Summary(modifier = Modifier.padding(horizontal = 16.dp))
    Spacer(modifier = Modifier.height(16.dp))
    OtherHealthData()
}