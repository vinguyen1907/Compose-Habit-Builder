package com.example.monumental.presentation.screens.add_habit

import com.example.monumental.constants.enums.Frequency
import com.example.monumental.constants.enums.HabitColor
import com.example.monumental.domain.model.FrequencyData

sealed class AddHabitEvent {
    data class ChangeName(val name: String) : AddHabitEvent()
    data class ChangeFrequency(val frequency: FrequencyData) : AddHabitEvent()
    data object ChangeNotification : AddHabitEvent()
    data class ChangeColor(val color: HabitColor) : AddHabitEvent()
    data class ChangeGoal(val hours: Int?, val minutes: Int?) : AddHabitEvent()
    data class ChangeReminder(val hour: Int, val minute: Int) : AddHabitEvent()
    data class AddHabit(val onBack: () -> Unit) : AddHabitEvent()
}