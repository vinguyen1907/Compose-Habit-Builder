package com.example.monumental.domain.repository

import com.example.monumental.domain.model.Habit
import com.google.firebase.firestore.DocumentChange
import kotlinx.coroutines.flow.Flow

interface HabitRepository {
     fun getHabits(): Flow<List<DocumentChange>?>
    suspend fun addHabit(habit: Habit, onResult: (Throwable?) -> Unit )
}