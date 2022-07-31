package com.example.lbmeals.feature_meal_details.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter

@ExperimentalMaterial3Api
@Composable
fun MealDetailsScreen(
    navController: NavHostController,
    viewModel: MealDetailsViewModel = hiltViewModel(),
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
            Text(text = state.meal?.name ?: "")
            Text(text = state.meal?.area ?: "")

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                painter = rememberAsyncImagePainter(state.meal?.thumbnail),
                contentDescription = "mealThumb",
            )

            Text(text = state.meal?.instructions ?: "")
        }
    }
}