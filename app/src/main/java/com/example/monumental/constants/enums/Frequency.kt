package com.example.monumental.constants.enums

enum class Frequency {
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY,
    CUSTOM,
}

fun Frequency.toReadableString(): String {
    return when (this) {
        Frequency.DAILY -> "Daily"
        Frequency.WEEKLY -> "Weekly"
        Frequency.MONTHLY -> "Monthly"
        Frequency.YEARLY -> "Yearly"
        Frequency.CUSTOM -> "Custom"
    }
}