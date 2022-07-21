package com.example.lbmeals.feature_meal.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lbmeals.feature_categories.domain.model.Category
import com.example.lbmeals.feature_categories.presentation.screens.CategoryScreen
import com.example.lbmeals.feature_meal.domain.model.Meal
import com.example.lbmeals.feature_meal.presentation.screens.MealDetailsScreen
import com.example.lbmeals.feature_meal.presentation.screens.MealsScreen

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainScreens.CategoryScreen.name
    ) {
        composable(MainScreens.CategoryScreen.name){
            CategoryScreen(navController = navController)
        }

        composable(
            route = MainScreens.MealsScreen.name + "/{category}",
            arguments = listOf(
                navArgument(name = "category") {
                    type = NavType.StringType
                }
            )
        ){ backStackEntry ->
            backStackEntry.arguments?.getParcelable<Category>("category")?.let {
                MealsScreen(
                    navController = navController,
                    it
                )
            }
        }
        composable(
            route = MainScreens.MealDetailsScreen.name + "/{meal}",
            arguments = listOf(
                navArgument(name = "meal") {
                    type = NavType.StringType
                }
            )
        ){ backStackEntry ->
            backStackEntry.arguments?.getParcelable<Meal>("meal")?.let {
                MealDetailsScreen(
                    navController = navController,
                    it
                )
            }
        }
    }
}