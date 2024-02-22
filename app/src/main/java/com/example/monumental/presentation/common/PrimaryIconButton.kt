package com.example.monumental.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.monumental.R

@Composable
fun PrimaryIconButton(
    @DrawableRes icon: Int,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    IconButton(
        content = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = contentDescription
            )
        },
        onClick = onClick,
        colors = IconButtonDefaults.filledTonalIconButtonColors(
            containerColor = colorResource(id = R.color.eclipse_10),
            contentColor = colorResource(id = R.color.eclipse),
        )
    )
}