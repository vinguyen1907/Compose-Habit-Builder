package com.example.monumental.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.monumental.R
import com.example.monumental.domain.model.Quote
import com.example.monumental.ui.theme.MonumentalTheme

@Composable
fun Quotes(
    modifier: Modifier = Modifier,
    quote: Quote,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(color = colorResource(id = R.color.white))
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.5f)
                .align(Alignment.BottomEnd),
            painter = painterResource(id = R.drawable.img_quote),
            contentDescription = "Quote image",
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.68f)
                .padding(top = 20.dp, bottom = 20.dp, start = 15.dp)
        ) {
            Text(
                text = quote.text,
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                "- ${quote.author}", style = MaterialTheme.typography.bodyLarge,
                color = colorResource(id = R.color.eclipse).copy(alpha = 0.5f)
            )
        }

    }
}

@Preview
@Composable
fun QuotesPrev() {
    MonumentalTheme {
        Quotes(
            quote = Quote(
                "We first make our habits, and then our habits makes us.",
                "AUTHOR NAME"
            )
        )
    }
}