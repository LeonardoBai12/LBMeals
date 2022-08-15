package com.example.lbmeals.feature_meals.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.lbmeals.core.navigation.MainScreens
import com.example.lbmeals.feature_meals.presentation.components.MealCard
import com.example.lbmeals.feature_meals.presentation.components.MealShimmerCard
import com.example.lbmeals.util.components.DefaultAppBar

@ExperimentalMaterial3Api
@Composable
fun MealsScreen(
    navController: NavHostController,
    viewModel: MealViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

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
                            contentDescription ="Arrow Back",
                        )
                    }
                },
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
            if (state.loading) {
                MealsShimmerColumn()
            } else {
                MealsColumn(state, navController)
            }
        }
    }
}

@Composable
private fun MealsShimmerColumn() {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(3) {
            MealShimmerCard()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun MealsColumn(
    state: MealState,
    navController: NavHostController
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.meals) { meal ->
            MealCard(
                meal = meal,
                onClick = {
                    navController.navigate(
                        MainScreens.MealDetailsScreen.route + "/${meal.id}"
                    )
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}