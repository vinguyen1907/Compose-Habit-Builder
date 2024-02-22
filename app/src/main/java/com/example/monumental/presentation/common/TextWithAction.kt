package com.example.monumental.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.monumental.R

@Composable
fun TextWithAction(
    label: String,
    actionText: String,
    onAction: () -> Unit,
) {
    Row {
        Text(
            text = "$label ",
            style = MaterialTheme.typography.titleSmall.copy(
                color = colorResource(id = R.color.eclipse),
                fontWeight = FontWeight.Medium,
            ),
        )
        Text(text = actionText,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.clickable {
                onAction()
            }
        )
    }
}