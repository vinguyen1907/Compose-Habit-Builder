package com.example.monumental.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.monumental.R

@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    containerColor: Color = colorResource(id = R.color.secondary_bg_color),
    maxLines: Int = 1,
    isError: Boolean = false,
    errorText: String? = null,
) {
    var isVisible by rememberSaveable { mutableStateOf(false) }
    Column (
        modifier = modifier,

    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            maxLines = maxLines,
            leadingIcon = leadingIcon,
            trailingIcon = {
                val icon = if (isVisible) R.drawable.ic_eye else R.drawable.ic_eye_off
                val description = if (isVisible) "Hide password" else "Show password"
                val tint = if (isVisible) Color.Black else colorResource(id = R.color.primary_color)
                val alpha = if (isVisible) 0.5f else 1f
                IconButton(onClick = {
                    isVisible = !isVisible
                }) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = description,
                        colorFilter = ColorFilter.tint(tint.copy(alpha = alpha)),
                        modifier = Modifier.size(16.dp),
                    )
                }

            },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                disabledContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                focusedContainerColor = containerColor,
                errorContainerColor = containerColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedLeadingIconColor = colorResource(id = R.color.primary_color),
                unfocusedLeadingIconColor = colorResource(id = R.color.eclipse).copy(alpha = 0.5f),
            ),
            textStyle = MaterialTheme.typography.titleMedium.copy(
                color = colorResource(id = R.color.primary_color)
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            placeholder = placeholder,
            visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        )
        if (isError && errorText != null) {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = errorText,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = colorResource(id = R.color.sunset),
                    fontWeight = FontWeight.Medium,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}