package com.example.lbmeals.feature_meal.presentation

sealed class MealEvent {
    data class SearchedForMeal(val value: String): MealEvent()
}
