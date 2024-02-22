package com.example.monumental

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monumental.domain.usecases.app_entry.AppEntryUseCase
import com.example.monumental.presentation.screens.nav_graph.Route
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCase
) :ViewModel() {
    var showSplash by mutableStateOf(true)
        private set
    var startDestination by mutableStateOf(Route.StartingNavigation.route)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach { startFromHome ->
            startDestination = if (startFromHome) {
                val currentUser = FirebaseAuth.getInstance().currentUser
                if (currentUser != null) {
                    loadUserData(currentUser)
                }
                Route.AuthenticationNavigation.route
            } else {
                Route.StartingNavigation.route
            }
            delay(300)
            showSplash = false
        }.launchIn(viewModelScope)
    }
}

fun loadUserData(currentUser: FirebaseUser) {

}