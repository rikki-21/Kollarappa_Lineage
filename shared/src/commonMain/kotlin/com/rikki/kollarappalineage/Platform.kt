package com.rikki.kollarappalineage

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform