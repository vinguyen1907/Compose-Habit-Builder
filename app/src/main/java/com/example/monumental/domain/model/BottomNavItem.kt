package com.example.monumental.domain.model

import androidx.annotation.DrawableRes

data class BottomNavItem(
    @DrawableRes val activeIcon: Int,
    @DrawableRes val inactiveIcon: Int,
    val title: String,
)