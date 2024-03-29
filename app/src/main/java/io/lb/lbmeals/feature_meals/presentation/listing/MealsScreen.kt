package io.lb.lbmeals.feature_meals.presentation.listing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import io.lb.lbmeals.core.navigation.MainScreens
import io.lb.lbmeals.feature_meals.presentation.listing.components.MealCard
import io.lb.lbmeals.feature_meals.presentation.listing.components.MealShimmerCard
import io.lb.lbmeals.util.components.DefaultAppBar
import io.lb.lbmeals.util.components.DefaultErrorScreen
import io.lb.lbmeals.util.components.DefaultSearchBar
import io.lb.lbmeals.util.showToast
import kotlinx.coroutines.flow.collectLatest

@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun MealsScreen(
    navController: NavHostController,
    viewModel: MealViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val context = LocalContext.current
    val search = remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = "MealsScreen") {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is MealViewModel.UiEvent.ShowToast -> {
                    context.showToast(event.message)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            DefaultAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Arrow Back",
                            tint = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                },
                title = {
                    Text(
                        text = viewModel.category,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    DefaultSearchBar(
                        search = search,
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp
                        ),
                        onSearch = { meal ->
                            viewModel.onEvent(MealEvent.SearchedForMeal(meal))
                        },
                        isEnabled = !state.loading
                    )
                }

                if (!state.loading) {
                    state.meals.takeIf { meals ->
                        meals.isNotEmpty()
                    }?.let {
                        mealsColumn(state, navController)
                    } ?: takeIf {
                        search.value.isEmpty()
                    }?.let {
                        item {
                            DefaultErrorScreen {
                                viewModel.getMealsByCategory()
                            }
                        }
                    }
                } else {
                    mealsShimmerColumn()
                }
            }
        }
    }
}

private fun LazyListScope.mealsShimmerColumn() {
    items(3) {
        MealShimmerCard()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@ExperimentalMaterial3Api
private fun LazyListScope.mealsColumn(
    state: MealState,
    navController: NavHostController
) {
    items(state.meals) { meal ->
        MealCard(
            meal = meal,
            onClick = {
                navController.navigate(
                    MainScreens.MealDetailsScreen.route + "/${meal.id}"
                )
            },
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}
