package com.example.lbmeals.feature_categories.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.lbmeals.feature_categories.domain.model.Category
import com.example.lbmeals.feature_categories.presentation.CategoryViewModel
import com.example.lbmeals.feature_categories.presentation.components.CategoryCard
import kotlinx.coroutines.launch

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
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.categories) { category ->
                    CategoryCard(
                        category = category,
                        onClick = {},
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}