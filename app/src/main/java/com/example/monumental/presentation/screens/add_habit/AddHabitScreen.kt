package com.example.monumental.presentation.screens.add_habit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.monumental.R
import com.example.monumental.constants.enums.HabitColor
import com.example.monumental.constants.enums.SuggestedHabit
import com.example.monumental.constants.enums.getColor
import com.example.monumental.constants.enums.toReadableString
import com.example.monumental.presentation.common.PrimaryCustomAppBar
import com.example.monumental.presentation.common.TextInput
import com.example.monumental.presentation.common.TrackingItem
import com.example.monumental.presentation.screens.add_habit.components.FAB
import com.example.monumental.presentation.screens.add_habit.components.SelectFrequencyBottomSheet
import com.example.monumental.presentation.screens.add_habit.components.SetGoalDialog
import com.example.monumental.presentation.screens.add_habit.components.SetReminderDialog
import com.example.monumental.presentation.screens.add_habit.components.SuggestedHabitItem
import com.example.monumental.presentation.screens.sign_in.components.LoadingScreen
import com.example.monumental.ui.theme.MonumentalTheme
import com.example.monumental.utils.Dimens.PageHorizontalPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHabitScreen(
    viewModel: AddHabitViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {

    val state = viewModel.state
    val onEvent = viewModel::onEvent
    var enteringGoal by remember { mutableStateOf(false) }
    var selectingFrequency by remember { mutableStateOf(false) }
    var changingReminder by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.img_home_bg),
                alignment = Alignment.BottomCenter,
                contentScale = ContentScale.FillWidth,
            ),
        containerColor = colorResource(id = R.color.primary_bg_color),
        topBar = {
            PrimaryCustomAppBar(
                title = "New Habit",
                onNavigationIconClick = onBack
            )
        },
        floatingActionButton = {
            FAB(
                onAddHabit = {
                    onEvent(AddHabitEvent.AddHabit)
                    onBack()
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .paint(
                    painter = painterResource(id = R.drawable.img_home_bg),
                    alignment = Alignment.BottomCenter,
                    contentScale = ContentScale.FillWidth,
                )
        ) {
            Column(
                modifier = Modifier,
            ) {
                TextInput(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = PageHorizontalPadding, vertical = 8.dp),
                    value = state.name,
                    onValueChange = {
                        viewModel.onEvent(AddHabitEvent.ChangeName(it))
                    },
                    placeholder = {
                        Text(
                            text = "Habit",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = colorResource(id = R.color.eclipse).copy(alpha = 0.5f),
                                fontWeight = FontWeight.Medium,
                            )
                        )
                    },
                    containerColor = colorResource(id = R.color.white),
                    isError = !state.habitTitleValidation.isValid,
                    errorText = state.habitTitleValidation.message,
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = PageHorizontalPadding),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(SuggestedHabit.entries.size) { index ->
                        val habit = SuggestedHabit.entries[index]
                        SuggestedHabitItem(
                            habit = habit,
                            isSelected = state.name == habit.toReadableString(),
                            onSuggestedHabitSelected = {
                                onEvent(AddHabitEvent.ChangeName(it.toReadableString()))
                            })
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = PageHorizontalPadding),
                    shape = MaterialTheme.shapes.medium,
                    color = colorResource(id = R.color.white)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = PageHorizontalPadding, top = 16.dp),
                    ) {
                        Text(
                            "Frequently",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                state.frequency.toReadableString(),
                                style = MaterialTheme.typography.titleMedium
                            )
                            TextButton(onClick = { selectingFrequency = !selectingFrequency }) {
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

//                        CustomDropdown(
//                            modifier = Modifier.fillMaxWidth(),
//                            expanded = selectingFrequency,
//                            selectedIndex = Frequency.entries.indexOf(state.frequency),
//                            items = Frequency.entries.map { it.toReadableString() },
//                            onSelect = {
//                                onEvent(AddHabitEvent.ChangeFrequency(Frequency.entries[it]))
//                                selectingFrequency = false
//                            },
//                            onDismissRequest = {
//                                selectingFrequency = false
//                            }
//                        ) {
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth(),
//                                verticalAlignment = Alignment.CenterVertically,
//                                horizontalArrangement = Arrangement.SpaceBetween
//                            ) {
//                                Text(
//                                    state.frequency.toReadableString(),
//                                    style = MaterialTheme.typography.titleMedium
//                                )
//                                TextButton(onClick = { selectingFrequency = !selectingFrequency }) {
//                                    Text(
//                                        "Change",
//                                        style = MaterialTheme.typography.titleMedium,
//                                        color = colorResource(id = R.color.primary_color)
//                                    )
//                                    Icon(
//                                        modifier = Modifier.align(Alignment.CenterVertically),
//                                        painter = painterResource(id = R.drawable.ic_chevron_right),
//                                        contentDescription = null,
//                                        tint = colorResource(id = R.color.primary_color)
//                                    )
//                                }
//                            }
//                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = PageHorizontalPadding),
                    shape = MaterialTheme.shapes.medium,
                    color = colorResource(id = R.color.white)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = PageHorizontalPadding),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Goal",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )
                        TextButton(
                            onClick = {
                                enteringGoal = true
                            }
                        ) {
                            Text(
                                viewModel.getGoalInString(),
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

                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = PageHorizontalPadding),
                    shape = MaterialTheme.shapes.medium,
                    color = colorResource(id = R.color.white)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = PageHorizontalPadding),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Reminder",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )
                        TextButton(onClick = {
                            changingReminder = true
                        }) {
                            Text(
                                String.format("%02d:%02d", state.reminder.hour, state.reminder.minute),
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

                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = PageHorizontalPadding),
                    shape = MaterialTheme.shapes.medium,
                    color = colorResource(id = R.color.white)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = PageHorizontalPadding),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Notification",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Switch(
                            checked = state.notification,
                            onCheckedChange = {
                                onEvent(AddHabitEvent.ChangeNotification)
                            },
                            colors = SwitchDefaults.colors(
                                checkedTrackColor = colorResource(id = R.color.eclipse_10),
                                checkedThumbColor = colorResource(id = R.color.eclipse),
                                uncheckedTrackColor = colorResource(id = R.color.eclipse_10),
                                uncheckedThumbColor = colorResource(id = R.color.eclipse),
                                uncheckedBorderColor = colorResource(id = R.color.eclipse),
                            )
                        )
                    }
                }

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = PageHorizontalPadding, vertical = 8.dp),
                    shape = MaterialTheme.shapes.medium,
                    color = colorResource(id = R.color.white)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = PageHorizontalPadding, top = 10.dp, bottom = 10.dp),
                    ) {
                        Text(
                            "Color",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            items(HabitColor.entries.size) { index ->
                                val currentColor = HabitColor.entries[index]
                                TrackingItem(
                                    color = currentColor.getColor(),
                                    size = 40.dp,
                                    onTap = {
                                        onEvent(AddHabitEvent.ChangeColor(currentColor))
                                    },
                                    content = {
                                        if (state.color == currentColor) {
                                            Icon(
                                                modifier = Modifier
                                                    .size(14.dp),
                                                painter = painterResource(id = R.drawable.ic_check),
                                                contentDescription = null,
                                                tint = colorResource(id = R.color.white)
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

    }

    if (enteringGoal) {
        SetGoalDialog(
            onDismissRequest = {
                enteringGoal = false
            },
            onSelectGoal = { hours, minutes ->
                onEvent(AddHabitEvent.ChangeGoal(hours, minutes))
            },
            hours = state.goal / 60,
            minutes = state.goal % 60
        )
    }

    if (selectingFrequency) {
        ModalBottomSheet(
            containerColor = colorResource(id = R.color.white),
            sheetState = rememberModalBottomSheetState(),
            dragHandle = {},
            shape = MaterialTheme.shapes.medium,
            onDismissRequest = {
                selectingFrequency = false
            }) {
            SelectFrequencyBottomSheet(
                modifier = Modifier.padding(bottom = 20.dp),
                currentFrequency = state.frequency,
                onSaveFrequency = { frequency, days ->
                    onEvent(AddHabitEvent.ChangeFrequency(frequency))
                    selectingFrequency = false
                },
                onDismissRequest = {
                    selectingFrequency = false
                }
            )
        }
    }

    val timePickerState by remember { mutableStateOf(TimePickerState(0, 0, true)) }
    if (changingReminder) {
        SetReminderDialog(state = timePickerState,
            onDismissRequest = {
                changingReminder = false
            },
            onSave = {
                onEvent(AddHabitEvent.ChangeReminder(hour = timePickerState.hour, minute = timePickerState.minute))
                changingReminder = false
            }
        )
    }

    if (state.loading) {
        LoadingScreen()
    }

}


@Preview
@Composable
fun AddHabitScreenPrev() {
    MonumentalTheme {
        AddHabitScreen(
            onBack = {}
        )
    }
}