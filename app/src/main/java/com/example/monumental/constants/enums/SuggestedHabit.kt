package com.example.monumental.constants.enums

import androidx.annotation.DrawableRes
import com.example.monumental.R

enum class SuggestedHabit {
    READING,
    MEDITATION,
    EXERCISE,
    SLEEP,
    WATERING,
    STRETCHING,
}

fun SuggestedHabit.toReadableString(): String {
    return when (this) {
        SuggestedHabit.READING -> "Reading"
        SuggestedHabit.MEDITATION -> "Meditation"
        SuggestedHabit.EXERCISE -> "Exercise"
        SuggestedHabit.SLEEP -> "Sleep"
        SuggestedHabit.WATERING -> "Watering"
        SuggestedHabit.STRETCHING -> "Stretching"
    }
}

/**
 * Returns the icon resource ID corresponding to the suggested habit.
 * @return The drawable resource ID of the habit's icon.
 */
fun SuggestedHabit.getIcon(): Int {
    return when (this) {
        SuggestedHabit.READING -> R.drawable.ic_reading
        SuggestedHabit.MEDITATION -> R.drawable.ic_meditation
        SuggestedHabit.EXERCISE -> R.drawable.ic_exercising
        SuggestedHabit.SLEEP -> R.drawable.ic_sleeping
        SuggestedHabit.WATERING -> R.drawable.ic_watering
        SuggestedHabit.STRETCHING -> R.drawable.ic_yoga
    }
}