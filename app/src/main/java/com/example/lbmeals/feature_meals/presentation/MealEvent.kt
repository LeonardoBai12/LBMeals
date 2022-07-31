package com.example.lbmeals.feature_meals.presentation

sealed class MealEvent {
    data class SearchedForMeal(val value: String): MealEvent()
}
