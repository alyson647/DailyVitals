package com.daily.vitals.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val name: String? = null,
    val email: String? = null,
    val profilePicture: String? = null
)
