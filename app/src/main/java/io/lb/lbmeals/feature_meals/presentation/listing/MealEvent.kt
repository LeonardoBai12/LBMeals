package io.lb.lbmeals.feature_meals.presentation.listing

sealed class MealEvent {
    data class SearchedForMeal(val filter: String) : MealEvent()
}
