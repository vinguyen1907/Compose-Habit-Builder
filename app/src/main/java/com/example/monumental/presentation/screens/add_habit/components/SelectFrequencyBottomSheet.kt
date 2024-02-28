package com.example.monumental.presentation.screens.add_habit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.monumental.R
import com.example.monumental.constants.enums.Frequency
import com.example.monumental.constants.enums.toReadableShortString
import com.example.monumental.constants.enums.toReadableString
import com.example.monumental.domain.model.CustomFrequencyData
import com.example.monumental.domain.model.DailyFrequencyData
import com.example.monumental.domain.model.FrequencyData
import com.example.monumental.domain.model.MonthlyFrequencyData
import com.example.monumental.domain.model.WeeklyFrequencyData
import com.example.monumental.domain.model.YearlyFrequencyData
import com.example.monumental.presentation.common.CustomBottomSheetLayout
import com.example.monumental.presentation.common.CustomDropdown
import com.example.monumental.presentation.common.TextInput
import com.example.monumental.ui.theme.MonumentalTheme
import com.example.monumental.utils.Dimens.PageHorizontalPadding
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectFrequencyBottomSheet(
    currentFrequencyData: FrequencyData,
    modifier: Modifier = Modifier,
    onSaveFrequency: (FrequencyData, List<DayOfWeek>) -> Unit,
    onDismissRequest: () -> Unit,
) {
    var selectingFrequency by remember { mutableStateOf(false) }
    var frequency by remember { mutableStateOf(currentFrequencyData.frequency) }
    var selectedDays by remember {
        mutableStateOf(
            listOf(
                LocalDate.now().dayOfWeek
            )
        )
    }
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    CustomBottomSheetLayout(
        modifier = modifier.fillMaxWidth(),
        state = sheetState,
        title = "Select Frequency",
        onDismissRequest = onDismissRequest,
        leftButtonText = "Cancel",
        onLeftButtonClick = {
            coroutineScope.launch {
                sheetState.hide()
                onDismissRequest()
            }
        },
        rightButtonText = "Save",
        onRightButtonClick = {
            val frequencyData = when (frequency) {
                Frequency.DAILY -> DailyFrequencyData()
                Frequency.WEEKLY -> WeeklyFrequencyData(
                    day = selectedDays.first()
                )
                Frequency.MONTHLY -> MonthlyFrequencyData(
                    dayOfMonth =
                )
                Frequency.YEARLY -> YearlyFrequencyData(
                    month = ,
                    dayOfMonth = ,
                )
                Frequency.CUSTOM -> CustomFrequencyData(
                    days = selectedDays
                )
            }
            onSaveFrequency(frequencyData, selectedDays)
            coroutineScope.launch {
                sheetState.hide()
                onDismissRequest()
            }
        }
    ) {
        CustomDropdown(
            modifier = Modifier.fillMaxWidth(),
            expanded = selectingFrequency,
            selectedIndex =
            Frequency.entries.indexOf(frequency),
            items = Frequency.entries.map { it.toReadableString() },
            onSelect = {
                frequency = Frequency.entries[it]
                selectingFrequency = false
            },
            onDismissRequest = {
                selectingFrequency = false
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = PageHorizontalPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    frequency.toReadableString(),
                    style = MaterialTheme.typography.titleMedium
                )
                TextButton(onClick = {
                    selectingFrequency = !selectingFrequency
                }) {
                    Text(
                        "Change",
                        style = MaterialTheme.typography.titleMedium,
                        color = colorResource(id = R.color.primary_color)
                    )
                    Icon(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        painter = painterResource(id = R.drawable.ic_chevron_right),
                        contentDescription = null,
                        tint = colorResource(id = R.color.primary_color)
                    )
                }
            }
        }

        when (frequency) {
            Frequency.WEEKLY -> {
                WeeklyFrequencyLayout(
                    selectedDay = selectedDays.first(),
                    onDaySelected = {
                        selectedDays = listOf(it)
                    }
                )
            }
            Frequency.MONTHLY -> {
                Text(
                    "Every month on ${LocalDate.now().dayOfWeek.toReadableString()}",
                    modifier = Modifier.padding(horizontal = PageHorizontalPadding)
                )
            }
            Frequency.YEARLY -> {
                Text(
                    "Every year on ${LocalDate.now().dayOfWeek.toReadableString()}",
                    modifier = Modifier.padding(horizontal = PageHorizontalPadding)
                )
            }
            Frequency.CUSTOM -> {
                CustomFrequencyLayout(
                    selectedDays = selectedDays,
                    onAdd = {
                        selectedDays += it
                    },
                    onRemove = { day ->
                        selectedDays = selectedDays.filter { it != day }
                    }
                )
            }
            else -> {
                Box {}
            }
        }
    }
}

@Composable
fun WeeklyFrequencyLayout(
    selectedDay: DayOfWeek,
    onDaySelected: (DayOfWeek) -> Unit,
) {
    val days = DayOfWeek.values().toList()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
    ) {
        items(days.size) { index ->
            val day = days[index]
            val isSelected = selectedDay == day
            Column(
                modifier = Modifier
                    .size(50.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(
                        color = if (isSelected) colorResource(id = R.color.orange) else colorResource(
                            id = R.color.primary_color_20
                        )
                    )
                    .aspectRatio(1.0f)
                    .clickable {
                        onDaySelected(day)
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    day.toReadableShortString().uppercase(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.eclipse).copy(alpha = 0.5f),
                )
            }
        }
    }
}

@Composable
fun CustomFrequencyLayout(
    selectedDays: List<DayOfWeek>,
    onAdd: (DayOfWeek) -> Unit,
    onRemove: (DayOfWeek) -> Unit,
) {
    val days = DayOfWeek.values().toList()

    Text(
        "Repeat every",
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(horizontal = PageHorizontalPadding, vertical = 8.dp)
    )
    Row(
        modifier = Modifier.padding(horizontal = PageHorizontalPadding, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextInput(
            modifier = Modifier.fillMaxWidth(fraction = 0.5f),
            value = "1", onValueChange = {},
            keyboardType = KeyboardType.Number,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            "weeks",
            style = MaterialTheme.typography.titleSmall,
        )
    }
    Text(
        "Repeat on",
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(horizontal = PageHorizontalPadding, vertical = 8.dp)
    )
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
    ) {
        items(days.size) { index ->
            val thisDay = days[index]
            val isSelected = selectedDays.contains(thisDay)
            Column(
                modifier = Modifier
                    .size(50.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(
                        color = if (isSelected) colorResource(id = R.color.orange) else colorResource(
                            id = R.color.primary_color_20
                        )
                    )
                    .aspectRatio(1.0f)
                    .clickable {
                        if (selectedDays.contains(thisDay)) {
                            onRemove(thisDay)
                        } else {
                            onAdd(thisDay)
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    thisDay.toReadableShortString().uppercase(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.eclipse).copy(alpha = 0.5f),
                )
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 700)
@Composable
fun BottomSheetContentPrev() {
    MonumentalTheme {
        SelectFrequencyBottomSheet(
            currentFrequencyData = DailyFrequencyData(),
            onSaveFrequency = { _, _ -> },
            onDismissRequest = {}
        )
    }
}