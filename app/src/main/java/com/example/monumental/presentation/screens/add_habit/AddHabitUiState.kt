package com.example.monumental.presentation.screens.add_habit

import com.example.monumental.constants.enums.Frequency
import com.example.monumental.constants.enums.HabitColor
import com.example.monumental.presentation.validators.ValidationResult
import java.time.LocalTime

data class AddHabitUiState(
    val name: String = "",
    val frequency: Frequency = Frequency.DAILY,
    val notification: Boolean = true,
    val color: HabitColor = HabitColor.ORANGE,
    val goal: Int = 15,
    val reminder: LocalTime =  LocalTime.now(),
    val habitTitleValidation: ValidationResult = ValidationResult(true),

    val loading: Boolean = false,
    val error: String? = null,
)