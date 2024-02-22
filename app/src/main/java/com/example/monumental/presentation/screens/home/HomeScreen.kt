package com.example.monumental.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.monumental.R
import com.example.monumental.presentation.common.PrimaryCustomAppBar
import com.example.monumental.presentation.screens.home.components.HabitItem
import com.example.monumental.presentation.screens.home.components.Quotes
import com.example.monumental.ui.theme.MonumentalTheme
import com.example.monumental.utils.DateUtil
import com.example.monumental.utils.Dimens.PageHorizontalPadding
import com.example.monumental.utils.getFormattedDateOfWeek
import java.time.LocalDate

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    val days = DateUtil.getDaysList(LocalDate.now().minusDays(15), LocalDate.now())
    val lazyListState = rememberLazyListState()
    val habits = state.habits

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.img_home_bg),
                alignment = Alignment.BottomCenter,
                contentScale = ContentScale.FillWidth,
            ),
        containerColor = colorResource(id = R.color.primary_bg_color),
        topBar = {
            PrimaryCustomAppBar(
                title = "Home",
                navigationIcon = R.drawable.ic_menu,
                onNavigationIconClick = { },
                actions = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = "Not have user",
                        tint = Color.Gray
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(top = 14.dp),
        ) {
            Quotes(
                modifier = Modifier
                    .padding(horizontal = PageHorizontalPadding),
                quote = state.quote!!,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = PageHorizontalPadding)
                    .padding(top = 19.dp, bottom = 11.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "HABITS", style = MaterialTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.27f)
                        .padding(start = 18.dp),
                )
                LazyRow(
                    state = lazyListState,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(start = 15.dp, end = 15.dp),
                ) {
                    items(days.size) {
                        Column(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(MaterialTheme.shapes.medium)
                                .background(color = colorResource(id = R.color.white))
                                .aspectRatio(1.0f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                days[it].getFormattedDateOfWeek(),
                                style = MaterialTheme.typography.bodyMedium,
                                color = colorResource(id = R.color.eclipse).copy(alpha = 0.5f),
                            )
                            Text(
                                days[it].dayOfMonth.toString(),
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }
                    }
                }
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 10.dp)
            ) {
                items(habits.size) {index ->
                    HabitItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = PageHorizontalPadding),
                        state = lazyListState,
                        days = days,
                        habit = habits[index],
                    )
                }
            }
        }
    }

    LaunchedEffect(lazyListState.firstVisibleItemScrollOffset) {
    }
}



@Preview(showBackground = true, apiLevel = 33)
@Composable
fun HomeScreenPrev() {
    MonumentalTheme {
        HomeScreen()
    }
}