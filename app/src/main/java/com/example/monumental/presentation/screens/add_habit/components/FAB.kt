package com.example.monumental.presentation.screens.add_habit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.monumental.R
import com.example.monumental.ui.theme.MonumentalTheme

@Composable
fun FAB(
    onAddHabit: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Surface(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
            color = colorResource(id = R.color.white),
            shape = MaterialTheme.shapes.medium,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_face),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        "START THIS HABIT",
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        "Habits change into character. - Ovid",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = colorResource(id = R.color.eclipse).copy(
                            alpha = 0.5f
                        ),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
        IconButton(
            modifier = Modifier
                .size(52.dp)
                .clip(CircleShape)
                .background(colorResource(id = R.color.orange_20))
                .padding(6.dp)
            ,
            onClick = onAddHabit,
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = colorResource(id = R.color.orange)
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "Add",
                tint = colorResource(id = R.color.eclipse)
            )
        }
    }
}

@Preview
@Composable
fun FABPRev() {
    MonumentalTheme {

        FAB(
            onAddHabit = {}
        )
    }
}