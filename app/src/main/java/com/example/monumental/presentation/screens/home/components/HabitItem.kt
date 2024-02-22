package com.example.monumental.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.monumental.R
import com.example.monumental.constants.enums.HabitColor
import com.example.monumental.constants.enums.getColor
import com.example.monumental.domain.model.Habit
import java.time.LocalDate

@Composable
fun HabitItem(
    modifier: Modifier = Modifier,
    state: LazyListState,
    days: List<LocalDate>,
    habit: Habit,
) {
    println("Habit color: ${habit.title} ${habit.color}")
    val color = habit.color.getColor()

    Row(
        modifier = modifier
            .clip(
                RoundedCornerShape(
                    topStart = 12.dp,
                    bottomStart = 12.dp,
                )
            )
            .background(color = colorResource(id = R.color.white))
            .padding(top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            habit.title, style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.fillMaxWidth(fraction = 0.27f).padding(start = 18.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        LazyRow(
            state = state,
            contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(days.size) {
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .border(
                            width = 2.dp,
                            color = color.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(14.dp),
                        )
                        .padding(2.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = color)
                        .clickable { }
                )
            }
        }
    }
}
