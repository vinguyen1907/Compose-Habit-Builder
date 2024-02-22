package com.example.monumental.presentation.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monumental.data.data_source.quotesList
import com.example.monumental.domain.model.Habit
import com.example.monumental.domain.usecases.habits.HabitUseCases
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val habitsUseCases: HabitUseCases,
) : ViewModel() {
    var state by mutableStateOf(HomeUiState())
        private set

    private fun getRandomQuote() {
        val randomIndex = Random.nextInt(quotesList.size)
        state = state.copy(
            quote = quotesList[randomIndex]
        )
    }

    init {
        getRandomQuote()
        getHabits()
    }

    private fun getHabits() {
        viewModelScope.launch {
            try {
                habitsUseCases.getAllHabits().collect { docChanges ->
                    val newHabits = state.habits.toList().toMutableList()

                    docChanges?.forEach { docChange ->
                        val habit = docChange.document.toObject<Habit>()
                        when (docChange.type) {
                            DocumentChange.Type.ADDED -> {
                                newHabits.add(0, habit)
                            }

                            DocumentChange.Type.REMOVED -> {
                                newHabits -= habit
                            }

                            DocumentChange.Type.MODIFIED -> {
                                val index = newHabits.indexOfFirst { it.id == habit.id }
                                newHabits[index] = habit
                            }
                        }
                    }
                    state = state.copy(habits = newHabits)
                }
            } catch (e: Exception) {
                println("Get habits error: $e")
            }
        }
    }
}
