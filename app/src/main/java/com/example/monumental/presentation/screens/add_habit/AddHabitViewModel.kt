package com.example.monumental.presentation.screens.add_habit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monumental.domain.model.Habit
import com.example.monumental.domain.usecases.habits.HabitUseCases
import com.example.monumental.presentation.validators.ValidationResult
import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class AddHabitViewModel @Inject constructor(
    private val habitUseCases: HabitUseCases,
) : ViewModel() {
    var state by mutableStateOf(AddHabitUiState())
        private set

    fun onEvent(event: AddHabitEvent) {
        when (event) {
            is AddHabitEvent.ChangeName -> {
                state = state.copy(
                    name = event.name
                )
            }

            is AddHabitEvent.ChangeFrequency -> {
                state = state.copy(
                    frequencyData = event.frequency
                )
            }

            is AddHabitEvent.ChangeNotification -> {
                state = state.copy(
                    notification = !state.notification
                )
            }

            is AddHabitEvent.ChangeColor -> {
                state = state.copy(
                    color = event.color
                )
            }

            is AddHabitEvent.ChangeGoal -> {
                val hours = event.hours ?: (state.goal / 60)
                val minutes = event.minutes ?: (state.goal % 60)

                state = state.copy(
                    goal = hours * 60 + minutes
                )
            }

            is AddHabitEvent.ChangeReminder -> {
                state = state.copy(
                    reminder = LocalTime.of(event.hour, event.minute)
                )
            }

            is AddHabitEvent.AddHabit -> {

                if (validate()) {
                    viewModelScope.launch {
                        addHabit()
                    }
                    event.onBack()
                }
            }
        }
    }

    private fun validate(): Boolean {
        if (state.name.isEmpty()) {
            state = state.copy(
                habitTitleValidation = ValidationResult(false, "Name cannot be empty")
            )
            return false
        }
        return true
    }

    private suspend fun addHabit() {
        changeLoading(true)
        val habit = Habit(
            title = state.name,
            color = state.color,
            completed = false,
            frequencyData = state.frequencyData,
            notification = state.notification,
            goal = state.goal,
            reminder = state.reminder,
            createdAt = Timestamp.now(),
            updatedAt = Timestamp.now(),
        )
        habitUseCases.addHabit(
            habit = habit,
            onResult = { exception ->
                if (exception == null) {
                    state = state.copy(
                        loading = false,
                        error = null
                    )
                } else {
                    state = state.copy(
                        loading = false,
                        error = exception.message ?: "An error occurred"
                    )
                }
            }
        )
    }

    private fun changeLoading(loading: Boolean) {
        state = state.copy(
            loading = loading
        )
    }
}