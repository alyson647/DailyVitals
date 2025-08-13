package com.daily.vitals.domain.user.model

import com.daily.vitals.composeApp.database.User

fun User.toUserModel(): com.daily.vitals.domain.user.model.User = User(
    id = user_id,
    name = name.orEmpty(),
    email = email.orEmpty(),
    profilePicture = profile_picture.orEmpty()
)
