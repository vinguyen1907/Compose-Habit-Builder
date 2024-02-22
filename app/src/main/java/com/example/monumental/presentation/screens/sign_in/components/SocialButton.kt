package com.example.monumental.presentation.screens.sign_in.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.monumental.R

@Composable
fun SocialButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    text: String,
) {
    FilledTonalButton(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.white),
        ),
        contentPadding = PaddingValues(vertical = 12.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(23.dp)
            )
            Spacer(modifier = Modifier.width(30.dp))
            Text(
                text,
                style = MaterialTheme.typography.titleMedium.copy(color = colorResource(id = R.color.eclipse))
            )
        }
    }
}