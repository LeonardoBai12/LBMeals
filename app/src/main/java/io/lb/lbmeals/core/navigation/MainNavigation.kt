package io.lb.lbmeals.core.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.lb.lbmeals.feature_categories.presentation.CategoryScreen
import io.lb.lbmeals.feature_meal_details.presentation.MealDetailsScreen
import io.lb.lbmeals.feature_meals.presentation.listing.MealsScreen

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreens.CategoryScreen.route
    ) {
        composable(MainScreens.CategoryScreen.route) {
            CategoryScreen(navController = navController)
        }

        composable(
            route = MainScreens.MealsScreen.route + "/{category}",
            arguments = listOf(
                navArgument(name = "category") {
                    type = NavType.StringType
                }
            )
        ) {
            MealsScreen(navController = navController)
        }
        composable(
            route = MainScreens.MealDetailsScreen.route + "/{meal}",
            arguments = listOf(
                navArgument(name = "meal") {
                    type = NavType.StringType
                }
            )
        ) {
            MealDetailsScreen(navController = navController)
        }
    }
}
