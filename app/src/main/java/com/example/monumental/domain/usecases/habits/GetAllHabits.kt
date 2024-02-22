package com.example.monumental.domain.usecases.habits

import androidx.paging.PagingData
import com.example.monumental.domain.model.Habit
import com.example.monumental.domain.repository.HabitRepository
import com.google.firebase.firestore.DocumentChange
import kotlinx.coroutines.flow.Flow

class GetAllHabits(
    private val habitRepository: HabitRepository
) {
     operator fun invoke(): Flow<List<DocumentChange>?> {
        return habitRepository.getHabits()
    }
}