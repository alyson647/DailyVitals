package com.daily.vitals.model

import kotlinx.serialization.Serializable

@Serializable
data class Entry(
    val exercise: Boolean? = null,
    val fasting: Int? = null,
    val postMeal: Int? = null,
    val sleep: Int? = null,
    val weight: Double? = null
)
