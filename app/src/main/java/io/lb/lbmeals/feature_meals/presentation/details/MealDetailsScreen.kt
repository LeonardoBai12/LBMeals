package io.lb.lbmeals.feature_meal_details.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import io.lb.lbmeals.feature_meals.domain.model.Meal
import io.lb.lbmeals.feature_meals.presentation.details.MealDetailsState
import io.lb.lbmeals.feature_meals.presentation.details.MealDetailsViewModel
import io.lb.lbmeals.util.components.DefaultErrorScreen
import io.lb.lbmeals.util.components.shimmerAnimation
import io.lb.lbmeals.util.measuredIngredients
import io.lb.lbmeals.util.showToast
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterial3Api
@Composable
fun MealDetailsScreen(
    navController: NavHostController,
    viewModel: MealDetailsViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val context = LocalContext.current

    LaunchedEffect(key1 = "MealDetailsScreen") {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is MealDetailsViewModel.UiEvent.ShowToast -> {
                    context.showToast(event.message)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.loading) {
                MealDetailsShimmerColumn(it)
            } else {
                MealDetailsColumn(
                    state = state,
                    padding = it,
                    viewModel = viewModel,
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun MealDetailsShimmerColumn(it: PaddingValues) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .shimmerAnimation(),
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .padding(top = 300.dp),
            shape = RoundedCornerShape(32.dp),
        ) {
            MealDetailsShimmer()
        }
    }
}

@Composable
private fun MealDetailsColumn(
    state: MealDetailsState,
    padding: PaddingValues,
    viewModel: MealDetailsViewModel,
    navController: NavHostController,
) {
    val scrollState = rememberScrollState()

    state.meal?.let {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                painter = rememberAsyncImagePainter(state.meal.thumbnail),
                contentScale = ContentScale.Crop,
                contentDescription = "mealThumb",
            )

            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground,
                elevation = FloatingActionButtonDefaults.elevation(8.dp),
                onClick = {
                    navController.popBackStack()
                },
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back",
                )
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
                    .padding(top = 300.dp),
                shape = RoundedCornerShape(32.dp),
            ) {
                MealDetails(state.meal)
            }
        }
    } ?: run {
        DefaultErrorScreen {
            viewModel.getMealDetailsById()
        }
    }
}

@Composable
fun MealDetailsShimmer() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.5F)
                .height(32.dp)
                .padding(top = 18.dp, bottom = 4.dp)
                .shimmerAnimation(),
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.3F)
                .height(24.dp)
                .padding(bottom = 18.dp)
                .shimmerAnimation(),
        )

        Text(
            modifier = Modifier.padding(bottom = 18.dp),
            text = "Ingredients",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        repeat(3) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Start,
            ) {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = "ingredient"
                )
                Spacer(modifier = Modifier.width(16.dp))
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth(0.5F)
                        .height(24.dp)
                        .padding(bottom = 8.dp)
                        .shimmerAnimation(),
                )
            }
        }

        Text(
            modifier = Modifier.padding(top = 18.dp),
            text = "Instructions",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun MealDetails(meal: Meal?) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(
                top = 18.dp,
                start = 18.dp,
                end = 18.dp,
            ),
            text = meal?.name ?: "",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )

        Text(
            text = meal?.area ?: "",
            fontSize = 18.sp,
        )

        Text(
            modifier = Modifier.padding(vertical = 18.dp),
            text = "Ingredients",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        meal?.measuredIngredients()?.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Start,
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = "ingredient")
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = it,
                    fontSize = 18.sp,
                )
            }
        }

        Text(
            modifier = Modifier.padding(top = 18.dp),
            text = "Instructions",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            modifier = Modifier.padding(18.dp),
            text = meal?.instructions ?: "",
            textAlign = TextAlign.Justify,
            fontSize = 18.sp,
        )
    }
}
