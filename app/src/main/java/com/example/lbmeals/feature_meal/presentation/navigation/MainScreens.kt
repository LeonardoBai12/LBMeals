package com.example.lbmeals.feature_meal.presentation.navigation

enum class MainScreens {
    CategoryScreen,
    MealsScreen,
    MealDetailsScreen;
    companion object {
        fun fromRoute(route: String?): MainScreens
                = when (route?.substringBefore("/")) {
            CategoryScreen.name -> CategoryScreen
            MealsScreen.name -> MealsScreen
            MealDetailsScreen.name -> MealDetailsScreen
            null -> CategoryScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}
