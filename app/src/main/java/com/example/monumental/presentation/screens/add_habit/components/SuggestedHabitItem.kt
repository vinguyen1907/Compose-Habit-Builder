package com.example.monumental.presentation.screens.add_habit.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.monumental.R
import com.example.monumental.constants.enums.SuggestedHabit
import com.example.monumental.constants.enums.getIcon
import com.example.monumental.constants.enums.toReadableString
import com.example.monumental.ui.theme.MonumentalTheme

@Composable
fun SuggestedHabitItem(
    modifier: Modifier = Modifier,
    habit: SuggestedHabit,
    isSelected: Boolean,
    onSuggestedHabitSelected: (SuggestedHabit) -> Unit
) {
    Surface(
        modifier = modifier
            .clickable {
                onSuggestedHabitSelected(habit)
            },
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(
            width = 1.dp,
            color = colorResource(id = R.color.eclipse_10),
        ),
        color = if (isSelected) colorResource(id = R.color.primary_color_20) else colorResource(
            id = R.color.white
        )
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = habit.getIcon()),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Text(
                habit.toReadableString(),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = colorResource(id = R.color.primary_color)
                ),
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
            )
        }

    }
}

@Preview
@Composable
fun SuggestedHabitPrev() {
    MonumentalTheme {
        SuggestedHabitItem(
            habit = SuggestedHabit.WATERING,
            isSelected = false,
            onSuggestedHabitSelected = {}
        )
    }
}