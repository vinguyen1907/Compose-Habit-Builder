package com.example.monumental.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import com.example.monumental.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    title: String? = null,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            if (navigationIcon != null)
                navigationIcon()
        },
        title = {
            if (title != null) {
                Text(
                    title,
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center,
                )
            }
        },
        actions = actions ?: {},
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorResource(id = R.color.primary_bg_color),
        )
    )
}

@Composable
fun PrimaryCustomAppBar(
    title: String? = null,
    @DrawableRes navigationIcon: Int = R.drawable.ic_arrow_left,
    onNavigationIconClick: () -> Unit,
    actions: @Composable (RowScope.() -> Unit)? = null,
) {
    CustomAppBar(
        title = title,
        navigationIcon = {
            DefaultAppBarLeading(
                icon = navigationIcon,
                onClick = onNavigationIconClick
            )
        },
        actions = actions
    )
}