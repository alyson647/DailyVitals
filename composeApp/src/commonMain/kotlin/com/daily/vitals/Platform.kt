package com.daily.vitals

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform