package com.example.monumental.presentation.screens.add_habit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.monumental.R
import com.example.monumental.presentation.common.CustomFilledTonalButton
import com.example.monumental.utils.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetReminderDialog(
    modifier: Modifier = Modifier,
    state: TimePickerState,
    onDismissRequest: () -> Unit,
    onSave: () -> Unit,
) {
    AlertDialog(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(color = colorResource(id = R.color.white))
            .padding(vertical = Dimens.PageHorizontalPadding)
            .wrapContentWidth()
            .wrapContentHeight(),
        onDismissRequest = { /*TODO*/ }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TimePicker(

                state = state,
                colors = TimePickerDefaults.colors(
                    timeSelectorSelectedContainerColor = colorResource(id = R.color.primary_color),
                    timeSelectorUnselectedContainerColor = colorResource(id = R.color.primary_color_20),
                ),
            )
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.PageHorizontalPadding),
                shape = MaterialTheme.shapes.small,
                onClick = onDismissRequest,
            ) {
                Text(
                    "Cancel",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = colorResource(id = R.color.primary_color),
                    )
                )
            }
            CustomFilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.PageHorizontalPadding),
                onClick = onSave,
                text = "Save"
            ) {
            }
        }

    }
}