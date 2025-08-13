package com.daily.vitals.domain.entry.model

import com.daily.vitals.composeApp.database.Entry

fun Entry.toEntryModel(): com.daily.vitals.domain.entry.model.Entry = Entry(
    id = entry_id,
    exercise = exercise ?: false,
    fasting = fasting?.toInt() ?: 0,
    postMeal = post_meal?.toInt() ?: 0,
    sleep = sleep?.toInt() ?: 0,
    weight = weight?.toFloat() ?: 0f
)
