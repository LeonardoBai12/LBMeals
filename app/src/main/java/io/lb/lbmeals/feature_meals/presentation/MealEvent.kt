package io.lb.lbmeals.feature_meals.presentation

sealed class MealEvent {
    data class SearchedForMeal(val value: String): MealEvent()
}
