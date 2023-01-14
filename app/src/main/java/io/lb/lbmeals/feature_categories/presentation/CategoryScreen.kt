package io.lb.lbmeals.feature_categories.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import io.lb.lbmeals.R
import io.lb.lbmeals.core.navigation.MainScreens
import io.lb.lbmeals.feature_categories.presentation.components.CategoryCard
import io.lb.lbmeals.feature_categories.presentation.components.CategoryShimmerCard
import io.lb.lbmeals.util.showToast
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterial3Api
@Composable
fun CategoryScreen(
    navController: NavHostController,
    viewModel: CategoryViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val context = LocalContext.current

    LaunchedEffect(key1 = "CategoryScreen") {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is CategoryViewModel.UiEvent.ShowToast -> {
                    context.showToast(event.message)
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 24.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.Top,
        ) {
            item(span = { GridItemSpan(2) }) {
                CategoryHeader()
            }
            if (!state.loading) {
                state.categories.takeIf { categories ->
                    categories.isNotEmpty()
                }?.let {
                    categoriesColumn(state, navController)
                } ?: run {
                    // TODO fazer tela genérica de erro
                }
            } else {
                categoryShimmerColumn()
            }
        }
    }
}

@Composable
private fun CategoryHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.padding(top = 24.dp),
            painter = painterResource(
                id = if (isSystemInDarkTheme()) {
                    R.drawable.ic_lbio_meals_dark_theme
                } else {
                    R.drawable.ic_lbio_meals_light_theme
                }
            ),
            contentDescription = "lightThemeAppIntro"
        )

        Text(
            text = "Choose your favorite kind of food =)",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center
            )
        )
    }
}

private fun LazyGridScope.categoryShimmerColumn() {
    items(5) {
        CategoryShimmerCard()
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@ExperimentalMaterial3Api
private fun LazyGridScope.categoriesColumn(
    state: CategoryState,
    navController: NavHostController
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