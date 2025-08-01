package com.daily.vitals.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val profile: Profile,
)
