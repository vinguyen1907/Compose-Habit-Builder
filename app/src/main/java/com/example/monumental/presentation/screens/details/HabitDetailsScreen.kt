package com.example.monumental.presentation.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.monumental.R
import com.example.monumental.constants.enums.Frequency
import com.example.monumental.constants.enums.HabitColor
import com.example.monumental.constants.enums.getColor
import com.example.monumental.constants.enums.toReadableShortString
import com.example.monumental.domain.model.DailyFrequencyData
import com.example.monumental.domain.model.Habit
import com.example.monumental.presentation.common.PrimaryCustomAppBar
import com.example.monumental.presentation.common.PrimaryIconButton
import com.example.monumental.ui.theme.MonumentalTheme
import com.example.monumental.utils.Dimens.PageHorizontalPadding
import com.google.firebase.Timestamp
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.YearMonth

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun HabitDetailsScreen(
    onBack: () -> Unit,
) {
    val habit = Habit(
        id = "1",
        title = "Drink water",
        color = HabitColor.ORANGE,
        frequencyData = DailyFrequencyData(),
        notification = true,
        reminder = LocalTime.of(12, 0),
        goal = 8,
        completed = false,
        createdAt = Timestamp.now(),
        updatedAt = Timestamp.now(),
    )
    var month by rememberSaveable {
        mutableStateOf(LocalDateTime.now().month)
    }

    Scaffold(
        containerColor = colorResource(id = R.color.primary_bg_color),
        topBar = {
            PrimaryCustomAppBar(
                title = habit.title,
                onNavigationIconClick = onBack,
                actions = {
                    PrimaryIconButton(icon = R.drawable.ic_edit) {

                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = PageHorizontalPadding),
                color = colorResource(id = R.color.white),
                shape = MaterialTheme.shapes.medium
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.img_teepee_swirly),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(60.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .background(colorResource(id = R.color.primary_bg_color)),
                    )
                    Column(
                        modifier = Modifier.padding(vertical = 15.dp),
                    ) {
                        Text(
                            text = habit.title,
                            style = MaterialTheme.typography.titleMedium,
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_notification),
                                contentDescription = null,
                                tint = colorResource(id = R.color.primary_color),
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                "Reminder at ${habit.reminder}",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Medium,
                                ),
                                color = colorResource(id = R.color.primary_color),
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_repeat),
                                contentDescription = null,
                                tint = colorResource(id = R.color.primary_color),
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                "Repeat ${habit.frequencyData?.frequency}",
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Medium,
                                ),
                                color = colorResource(id = R.color.primary_color),
                            )
                        }
                    }
                }
            }

            // Calendar
            Column(
                modifier = Modifier
                    .padding(horizontal = PageHorizontalPadding)
                    .clip(MaterialTheme.shapes.medium)
                    .background(colorResource(id = R.color.white))
                    .padding(
                        top = 10.dp,
                        bottom = 13.dp,
                        start = 4.dp,
                        end = 4.dp
                    ),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    IconButton(onClick = {
                        month = month.minus(1)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron_left),
                            contentDescription = null,
                            tint = colorResource(id = R.color.eclipse),
                        )
                    }
                    Text(text = month.name)
                    IconButton(onClick = {
                        month = month.plus(1)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron_right),
                            contentDescription = null,
                            tint = colorResource(id = R.color.eclipse),
                        )
                    }
                }


                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceAround,
                ) {
                    items(DayOfWeek.entries.size,
                        contentType = { index -> index }
                        ) { index ->
                        val dayOfWeek = DayOfWeek.entries[index]

                        Box(
                            Modifier
                                .weight(1f)
                        ) {
                            Text(
                                text = dayOfWeek.toReadableShortString().uppercase(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = colorResource(id = R.color.eclipse_50),
                                modifier = Modifier
                                    .padding(top = 16.dp, bottom = 8.dp),
                            )
                        }
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    val monthYear = YearMonth.of(2024, 2)

                    val firstDayOfMonth = monthYear.atDay(1)
                    val startDayOfWeek = firstDayOfMonth.dayOfWeek.value

                    items(startDayOfWeek - 1) { index ->
                        DayCell(
                            dayOfMonth = firstDayOfMonth.minusDays((startDayOfWeek - 1 - index).toLong()).dayOfMonth,
                            inSchedule = true,
                            isCompleted = true,
                            color = habit.color.getColor()
                        )
                    }

                    items(monthYear.lengthOfMonth()) { index ->
                        val dayOfMonth = index + 1

                        DayCell(
                            dayOfMonth = dayOfMonth,
                            inSchedule = true,
                            isCompleted = true,
                            color = habit.color.getColor()
                        )
                    }
                }
            }


        }
    }
}

@Composable
fun DayCell(
    modifier: Modifier = Modifier,
    dayOfMonth: Int,
    inSchedule: Boolean,
    isCompleted: Boolean,
    color: Color,
) {

    Surface(
        modifier = modifier,
        color = colorResource(id = R.color.primary_bg_color),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = 10.dp,
                    bottom = 5.dp,
                    start = 5.dp,
                    end = 5.dp,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = dayOfMonth.toString(),
                style = MaterialTheme.typography.titleSmall,
            )
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
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

@Preview(showBackground = true)
@Composable
fun HabitDetailsScreenPrev() {
    MonumentalTheme {
        HabitDetailsScreen(
            onBack = {}
        )
    }
}