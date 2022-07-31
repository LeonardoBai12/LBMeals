package com.example.lbmeals.core.navigation

sealed class MainScreens(val route: String) {
    object CategoryScreen: MainScreens("categories_screen")
    object MealsScreen: MainScreens("meals_screen")
    object MealDetailsScreen: MainScreens("meal_details_screen")
}