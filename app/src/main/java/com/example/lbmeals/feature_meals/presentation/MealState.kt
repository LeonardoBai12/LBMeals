package com.example.lbmeals.feature_meals.presentation

import com.example.lbmeals.feature_meals.domain.model.Meal

data class MealState(
    val meals: List<Meal> = emptyList(),
)
