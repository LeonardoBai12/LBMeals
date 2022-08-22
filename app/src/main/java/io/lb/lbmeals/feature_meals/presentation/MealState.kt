package io.lb.lbmeals.feature_meals.presentation

import io.lb.lbmeals.feature_meals.domain.model.Meal

data class MealState(
    val meals: List<Meal> = emptyList(),
    val loading: Boolean = true,
)
