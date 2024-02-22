package com.example.monumental.presentation.screens.home

import androidx.paging.PagingData
import com.example.monumental.domain.model.Habit
import com.example.monumental.domain.model.Quote
import kotlinx.coroutines.flow.Flow

data class HomeUiState(
    val quote: Quote? = null,
    val habits: List<Habit> = emptyList()
)