package com.example.monumental.presentation.screens.add_habit

import com.example.monumental.constants.enums.HabitColor
import com.example.monumental.domain.model.DailyFrequencyData
import com.example.monumental.domain.model.FrequencyData
import com.example.monumental.presentation.validators.ValidationResult
import java.time.LocalTime

data class AddHabitUiState(
    val name: String = "",
    val frequencyData: FrequencyData = DailyFrequencyData(),
    val notification: Boolean = true,
    val color: HabitColor = HabitColor.ORANGE,
    val goal: Int = 15,
    val reminder: LocalTime =  LocalTime.now(),
    val habitTitleValidation: ValidationResult = ValidationResult(true),

    val loading: Boolean = false,
    val error: String? = null,
) {
    val goalInString: String
        get() {
            val hours = goal / 60
            val minutes = goal % 60
            val hoursText = if (hours == 0) "" else "${hours}h "
            val minutesText = if (hours == 0) "$minutes mins" else "${minutes}m"
            return "$hoursText$minutesText"
        }
}