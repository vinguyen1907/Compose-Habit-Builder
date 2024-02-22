package com.example.monumental.presentation.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.monumental.R

@Composable
fun CustomFilledTonalButton(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(12.dp),
    shape: Shape = RoundedCornerShape(8.dp),
    containerColor: Color = colorResource(id = R.color.primary_color),
    contentColor: Color = colorResource(id = R.color.eclipse),
    disabledContainerColor: Color = colorResource(id = R.color.primary_color).copy(alpha = 0.2f),
    disabledContentColor: Color = colorResource(id = R.color.eclipse).copy(alpha = 0.2f),
    onClick: () -> Unit,
    text: String? = null,
    content: (@Composable () -> Unit)? = null,
) {
    assert(text != null || content != null)

    FilledTonalButton(
        modifier = modifier,
        contentPadding = contentPadding,
        shape = shape,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor,
        )
    ) {
        if (text != null) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = colorResource(id = R.color.eclipse),
                )
            )
        } else {
            if (content != null)
                content()
        }
    }
}