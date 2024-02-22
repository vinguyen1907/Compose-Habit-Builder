package com.example.monumental.presentation.screens.sign_up.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.monumental.R
import com.example.monumental.utils.Dimens

@Composable
fun CheckboxTile(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    title: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.PageHorizontalPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier
                .height(20.dp)
                .clip(shape = RoundedCornerShape(12.dp)),
            colors = CheckboxDefaults.colors(
                checkedColor = colorResource(id = R.color.primary_color),
                uncheckedColor = colorResource(id = R.color.primary_color),
                checkmarkColor = colorResource(id = R.color.eclipse),
            )
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                color = colorResource(id = R.color.eclipse),
                fontWeight = FontWeight.Medium,
            )
        )
    }
}