package com.example.monumental.presentation.screens.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.monumental.R
import com.example.monumental.presentation.common.CustomFilledTonalButton
import com.example.monumental.presentation.screens.onboarding.components.OnboardingPage
import com.example.monumental.presentation.screens.onboarding.components.OnboardingPageIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onEvent: (OnboardingEvent) -> Unit,
) {
    val pagerState = rememberPagerState {
        onboardingPages.size
    }
    val scope = rememberCoroutineScope()

    Column {
        HorizontalPager(state = pagerState) { index ->
            OnboardingPage(page = onboardingPages[index])
        }
        Spacer(modifier = Modifier.weight(1f))
        if (pagerState.currentPage == onboardingPages.size - 1) {
            CustomFilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                onClick = {
                    onEvent(OnboardingEvent.SaveAppEntry)
                }
            ) {
                Text(
                    "Get Started",
                    style = MaterialTheme.typography.titleMedium.copy(),
                    color = colorResource(id = R.color.eclipse)
                )
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(onboardingPages.size - 1)
                    }
                }) {
                    Text(
                        "Skip",
                        style = MaterialTheme.typography.titleLarge,
                        color = colorResource(id = R.color.eclipse)
                    )
                }
                OnboardingPageIndicator(
                    pageSize = onboardingPages.size,
                    selectedIndex = pagerState.currentPage,
                    selectedColor = colorResource(id = R.color.eclipse),
                    unselectedColor = colorResource(id = R.color.primary_color),
                )
                TextButton(onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }) {
                    Text(
                        "Next",
                        style = MaterialTheme.typography.titleLarge,
                        color = colorResource(id = R.color.eclipse)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }

}

//@Preview(showBackground = true)
//@Composable
//fun OnboardingScreenPrev() {
//    MonumentalTheme {
//        OnboardingScreen()
//    }
//}