package com.example.lbmeals.feature_categories.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.lbmeals.feature_categories.domain.model.Category
import com.example.lbmeals.feature_categories.presentation.components.CategoryCard

@ExperimentalMaterial3Api
@Composable
fun CategoryScreen(navController: NavHostController) {
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
            repeat(4) {
                CategoryCard(
                    Category("", "Beef", "https://www.themealdb.com/images/category/beef.png", "")
                ) {

                }
            }
        }
    }
}