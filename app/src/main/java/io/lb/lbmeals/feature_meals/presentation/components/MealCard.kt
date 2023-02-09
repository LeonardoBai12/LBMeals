package io.lb.lbmeals.feature_meals.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import io.lb.lbmeals.feature_meals.domain.model.Meal
import io.lb.lbmeals.util.components.shimmerAnimation

@ExperimentalMaterial3Api
@Composable
fun MealCard(meal: Meal, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        shadowElevation = 3.dp
    ) {
        val loading = remember {
            mutableStateOf(true)
        }

        if (loading.value) {
            Box(
                modifier = Modifier.height(200.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Image(
                modifier = Modifier.height(200.dp)
                    .fillMaxWidth(),
                painter = rememberAsyncImagePainter(
                    model = meal.thumbnail,
                    onSuccess = {
                        loading.value = false
                    }
                ),
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

@Composable
fun MealShimmerCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 3.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Spacer(
                modifier = Modifier.height(200.dp)
                    .fillMaxWidth()
                    .shimmerAnimation(),
            )

            Spacer(
                modifier = Modifier.height(24.dp)
                    .fillMaxWidth(0.7F)
                    .padding(
                        horizontal = 16.dp,
                        vertical = 12.dp,
                    ).shimmerAnimation(),
            )
        }
    }
}
