package com.example.monumental.presentation.screens.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.monumental.R
import com.example.monumental.presentation.screens.onboarding.OnboardingPageModel
import com.example.monumental.presentation.screens.onboarding.onboardingPages
import com.example.monumental.ui.theme.MonumentalTheme

@Composable
fun OnboardingPage(
    modifier: Modifier = Modifier,
    page: OnboardingPageModel,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Text(
            page.title,
            modifier = Modifier.fillMaxWidth(fraction = 0.845f),
            style = MaterialTheme.typography.displaySmall,
            color = colorResource(id = R.color.eclipse),
            textAlign = TextAlign.Center
        )
        Image(
            modifier = Modifier
                .fillMaxWidth(0.78f)
                .fillMaxHeight(0.54f),
            painter = painterResource(id = page.image),
            contentDescription = null
        )
        Text(
            modifier = Modifier.fillMaxWidth(fraction = 0.8f),
            text = buildAnnotatedString {
                withStyle(
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = colorResource(id = R.color.eclipse)
                    ).toSpanStyle()
                ) {
                    append("WE CAN")
                }
                withStyle(
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = colorResource(id = R.color.primary_color)
                    ).toSpanStyle()
                ) {
                    append(" HELP YOU")
                }
                withStyle(
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = colorResource(id = R.color.eclipse)
                    ).toSpanStyle()
                ) {
                    append(" TO BE A BETTER VERSION OF")
                }
                withStyle(
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = colorResource(id = R.color.primary_color)
                    ).toSpanStyle()
                ) {
                    append(" YOURSELF")
                }
            },
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPagePrev() {
    MonumentalTheme {
        OnboardingPage(
            page = onboardingPages.first()
        )
    }
}