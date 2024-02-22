package com.example.monumental.presentation.screens.add_habit.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.monumental.R

@Composable
fun TimeItem(
    modifier: Modifier = Modifier,
    hours: Int,
    minutes: Int,
    onTap: () -> Unit,
) {
    Surface(
        modifier = modifier.clickable {
            onTap()
        },
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(
            width = 1.dp,
            color = colorResource(id = R.color.eclipse_10),
        )
    ) {
        val hoursText = if (hours == 0) "" else "${hours}h "
        val minutesText = if (hours == 0) "$minutes mins" else " ${minutes}m"
        Text(
            "$hoursText$minutesText",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        )
    }
}