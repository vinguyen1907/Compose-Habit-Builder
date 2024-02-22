package com.example.monumental.presentation.screens.add_habit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.monumental.R
import com.example.monumental.presentation.common.CustomFilledTonalButton
import com.example.monumental.ui.theme.MonumentalTheme
import com.example.monumental.utils.Dimens
import com.example.monumental.utils.Dimens.PageHorizontalPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetGoalDialog(
    onDismissRequest: () -> Unit = {},
    hours: Int,
    minutes: Int,
    onSelectGoal: (Int?, Int?) -> Unit = { _, _ -> }
) {
    var hoursValue: Int? by remember { mutableStateOf(hours) }
    var minutesValue: Int? by remember { mutableStateOf(minutes) }

    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(color = colorResource(id = R.color.white))
            .padding(vertical = Dimens.PageHorizontalPadding)
            .wrapContentWidth()
            .wrapContentHeight(),
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Set Goal",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(horizontal = PageHorizontalPadding),
            )
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = PageHorizontalPadding)
            ) {
                item {
                    TimeItem(
                        modifier = Modifier.padding(end = 10.dp),
                        hours = 0, minutes = 15,
                        onTap = {
                            hoursValue = 0
                            minutesValue = 15
                        }
                    )
                    TimeItem(
                        modifier = Modifier.padding(end = 10.dp),
                        hours = 0, minutes = 30,
                        onTap = {
                            hoursValue = 0
                            minutesValue = 30
                        }
                    )
                    TimeItem(
                        modifier = Modifier.padding(end = 10.dp),
                        hours = 0, minutes = 45,
                        onTap = {
                            hoursValue = 0
                            minutesValue = 45
                        }
                    )
                    TimeItem(
                        modifier = Modifier.padding(end = 10.dp),
                        hours = 1, minutes = 0,
                        onTap = {
                            hoursValue = 1
                            minutesValue = 0
                        }
                    )
                    TimeItem(
                        hours = 1, minutes = 30,
                        onTap = {
                            hoursValue = 1
                            minutesValue = 30
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Custom")
            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.padding(horizontal = PageHorizontalPadding),
            ) {
                OutlinedTextField(
                    value = if (hoursValue != null) hoursValue.toString() else "",
                    onValueChange = {
                        hoursValue = it.toIntOrNull()
                    },
                    label = {
                        Text(text = "Hours")
                    },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    shape = MaterialTheme.shapes.medium,
                )
                Spacer(modifier = Modifier.width(10.dp))
                OutlinedTextField(
                    value = if (minutesValue != null) minutesValue.toString() else "",
                    onValueChange = {
                        minutesValue = it.toIntOrNull()
                    },
                    label = {
                        Text(text = "Minutes")
                    },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    shape = MaterialTheme.shapes.medium,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            CustomFilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = PageHorizontalPadding),
                onClick = {
                    onSelectGoal(hoursValue, minutesValue)
                    onDismissRequest()
                },
                text = "Save"
            )

        }
    }
}

@Preview
@Composable
fun SetGoalDialogPrev() {
    MonumentalTheme {
        SetGoalDialog(
            hours = 0,
            minutes = 0
        )
    }
}
