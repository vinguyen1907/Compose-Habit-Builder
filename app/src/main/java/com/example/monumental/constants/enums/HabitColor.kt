package com.example.monumental.constants.enums

import androidx.compose.ui.graphics.Color

enum class HabitColor {
    RED,
    ORANGE,
    YELLOW,
    GREEN,
    BLUE,
    PURPLE
}

fun HabitColor.getColor(): Color {
    return when (this) {
        HabitColor.RED -> Color.Red
        HabitColor.ORANGE -> Color(0xFFFFA500)
        HabitColor.YELLOW -> Color.Yellow
        HabitColor.GREEN -> Color.Green
        HabitColor.BLUE -> Color.Blue
        HabitColor.PURPLE -> Color(0xFF800080)
    }
}