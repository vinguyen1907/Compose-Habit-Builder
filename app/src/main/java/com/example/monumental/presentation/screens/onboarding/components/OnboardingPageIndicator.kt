package com.example.monumental.presentation.screens.onboarding.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.monumental.R

@Composable
fun OnboardingPageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unselectedColor: Color,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageSize) { index ->
            val isLast = index == pageSize - 1
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .padding(end = if (isLast)  0.dp else 10.dp)
                        .size(17.dp)
                        .border(
                            2.dp,
                            colorResource(id = R.color.eclipse).copy(alpha = 0.2f),
                            CircleShape
                        )
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                        .clip(CircleShape),
                )
            } else {
                Box(
                    modifier = Modifier
                        .padding(end = if (isLast)  0.dp else 10.dp)
                        .size(11.dp)
                        .clip(CircleShape)
                        .background(unselectedColor)
                        .padding(end = 10.dp, top = 100.dp),

                    )
            }
        }
    }
}