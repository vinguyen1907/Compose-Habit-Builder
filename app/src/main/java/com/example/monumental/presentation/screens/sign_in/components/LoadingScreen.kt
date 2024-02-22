package com.example.monumental.presentation.screens.sign_in.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.monumental.R

@Composable
fun LoadingScreen() {
    Box(
        modifier =  Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.black).copy(alpha = 0.5f)),
    ) {
        CircularProgressIndicator(
            color = colorResource(id = R.color.sunset),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}