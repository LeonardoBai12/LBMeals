package io.lb.lbmeals.feature_meal_details.presentation

import io.lb.lbmeals.feature_meals.domain.model.Meal

data class MealDetailsState(
    val meal: Meal? = null,
    val loading: Boolean = true,
)