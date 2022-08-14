package com.example.lbmeals.feature_categories.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.lbmeals.core.navigation.MainScreens
import com.example.lbmeals.feature_categories.presentation.components.CategoryCard
import com.example.lbmeals.feature_categories.presentation.components.CategoryShimmerCard

@ExperimentalMaterial3Api
@Composable
fun CategoryScreen(
    navController: NavHostController,
    viewModel: CategoryViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.loading) {
                CategoryShimmerColumn()
            } else {
                CategoriesColumn(state, navController)
            }
        }
    }
}

@Composable
private fun CategoryShimmerColumn() {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        columns = GridCells.Fixed(2),
    ) {
        items(5) {
            CategoryShimmerCard()
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun CategoriesColumn(
    state: CategoryState,
    navController: NavHostController
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        columns = GridCells.Fixed(2),
    ) {
        items(state.categories) { category ->
            CategoryCard(
                category = category,
                onClick = {
                    navController.navigate(
                        MainScreens.MealsScreen.route + "/${category.name}"
                    )
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}