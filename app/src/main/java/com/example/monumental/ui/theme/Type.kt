package com.example.monumental.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.monumental.R

val Klasik = FontFamily(
    Font(R.font.klasik_regular),
)

val Manrope = FontFamily(
    Font(R.font.manrope_regular),
    Font(R.font.manrope_medium, FontWeight.W500),
    Font(R.font.manrope_semi_bold, FontWeight.W600),
    Font(R.font.manrope_bold, FontWeight.W700),
)

val defaultTextColor = Color(0xFF573353)
// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Klasik,
        fontWeight = FontWeight.W400,
        fontSize = 40.sp,
        lineHeight = 40.sp,
        letterSpacing = 1.2.sp,
        color = defaultTextColor,
    ),
    displayMedium = TextStyle(
        fontFamily = Klasik,
        fontWeight = FontWeight.W400,
        fontSize = 36.sp,
        lineHeight = 32.sp,
        letterSpacing = 1.08.sp,
        color = defaultTextColor,
    ),
    displaySmall = TextStyle(
        fontFamily = Klasik,
        fontWeight = FontWeight.W400,
        fontSize = 32.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.96.sp,
        color = defaultTextColor,
    ),
    headlineLarge = TextStyle(
        fontFamily = Klasik,
        fontWeight = FontWeight.W400,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.72.sp,
        color = defaultTextColor,
    ),
    headlineMedium = TextStyle(
        fontFamily = Klasik,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp,
        lineHeight = 20.5.sp,
        letterSpacing = 0.54.sp,
        color = defaultTextColor,
    ),
    labelLarge = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.W700,
        fontSize = 22.sp,
        lineHeight = 24.sp,
        color = defaultTextColor,
    ),
    labelMedium = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.W700,
        fontSize = 20.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.6.sp,
        color = defaultTextColor,
    ),
    labelSmall = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.W700,
        fontSize = 18.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.54.sp,
        color = defaultTextColor,
    ),
    titleLarge = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.W700,
        fontSize = 17.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.51.sp,
        color = defaultTextColor,
    ),
    titleMedium = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.W700,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = (-0.48).sp,
        color = defaultTextColor,
    ),
    titleSmall = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.W700,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.42.sp,
        color = defaultTextColor,
    ),
    bodyLarge = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.W700,
        fontSize = 12.sp,
        lineHeight = 24.sp,
        color = defaultTextColor,
    ),
    bodyMedium = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.W700,
        fontSize = 10.sp,
        lineHeight = 13.sp,
        letterSpacing = 0.3.sp,
        color = defaultTextColor,
    ),
)