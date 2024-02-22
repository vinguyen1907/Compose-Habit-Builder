package com.example.monumental.presentation.screens.onboarding

sealed class OnboardingEvent {
    data object SaveAppEntry: OnboardingEvent()
}