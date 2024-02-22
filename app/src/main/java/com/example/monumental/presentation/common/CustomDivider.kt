package com.example.monumental.presentation.common

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.monumental.R

@Composable
fun CustomDivider(
    thickness: Int = 1,
    color: Int = R.color.primary_bg_color
) {
    Divider(
        thickness = 1.dp,
        color = colorResource(id = color),
    )
}