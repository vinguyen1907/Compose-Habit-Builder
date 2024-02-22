package com.example.monumental.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.example.monumental.R

@Composable
fun DefaultAppBarLeading(
    @DrawableRes icon: Int = R.drawable.ic_arrow_left,
    onClick: () -> Unit
) {
    PrimaryIconButton(
        icon = icon,
        contentDescription = "Back button",

        onClick = onClick
    )

}