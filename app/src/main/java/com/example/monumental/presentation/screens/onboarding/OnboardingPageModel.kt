package com.example.monumental.presentation.screens.onboarding

import androidx.annotation.DrawableRes
import com.example.monumental.R

data class OnboardingPageModel(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)


val onboardingPages = listOf(
    OnboardingPageModel(
        title = "WELCOME TO Monumental habits",
        description = "We can help you to be a better version of yourself.",
        image = R.drawable.img_onboarding_1,
    ),
    OnboardingPageModel(
        title = "CREATE NEW HABIT EASILY",
        description = "We can help you to be a better version of yourself.",
        image = R.drawable.img_onboarding_2,
    ),
    OnboardingPageModel(
        title = "KEEP TRACK OF YOUR PROGRESS",
        description = "We can help you to be a better version of yourself.",
        image = R.drawable.img_onboarding_3,
    ),
    OnboardingPageModel(
        title = "JOIN A SUPPORTIVE COMMUNITY",
        description = "We can help you to be a better version of yourself.",
        image = R.drawable.img_onboarding_4,
    ),
)