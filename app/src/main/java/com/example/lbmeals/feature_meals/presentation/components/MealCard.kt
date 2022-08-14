package com.example.lbmeals.feature_meals.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.lbmeals.feature_meals.domain.model.Meal

@ExperimentalMaterial3Api
@Composable
fun MealCard(meal: Meal, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clickable {
                onClick()
            },
        shadowElevation = 3.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                modifier = Modifier.height(200.dp)
                    .fillMaxWidth(),
                painter = rememberAsyncImagePainter(meal.thumbnail),
                contentScale = ContentScale.Crop,
                contentDescription = "mealThumb",
            )

            Text(
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 12.dp,
                ),
                text = meal.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
        }

    }
}