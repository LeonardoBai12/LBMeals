package io.lb.lbmeals.feature_meals.presentation.details

import io.lb.lbmeals.feature_meals.domain.model.Meal

data class MealDetailsState(
    val meal: Meal? = null,
    val loading: Boolean = true,
)
