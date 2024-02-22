package com.example.monumental.domain.usecases.habits

import com.example.monumental.domain.model.Habit
import com.example.monumental.domain.repository.HabitRepository

class AddHabit(
     private val habitRepository: HabitRepository
 ) {
    suspend operator fun invoke(habit: Habit, onResult: (Throwable?) -> Unit) {
        habitRepository.addHabit(habit, onResult)
    }
}
