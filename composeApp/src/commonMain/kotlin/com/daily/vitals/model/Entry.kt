package com.daily.vitals.model

import kotlinx.serialization.Serializable

@Serializable
data class Entry(
    val id: String = "",
    val exercise: Boolean = false,
    val fasting: Int = 0,
    val postMeal: Int = 0,
    val sleep: Int = 0,
    val weight: Double = 0.0
)
