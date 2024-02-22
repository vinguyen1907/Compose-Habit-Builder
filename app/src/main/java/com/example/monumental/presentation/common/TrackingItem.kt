package com.example.monumental.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TrackingItem(
    color: Color,
    size: Dp = 54.dp,
    onTap: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .size(size)
            .border(
                width = 2.dp,
                color = color.copy(alpha = 0.1f),
                shape = RoundedCornerShape(14.dp),
            )
            .padding(2.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = color)
            .clickable {
                onTap()
            },
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}