package com.example.lbmeals.feature_meal.presentation

import com.example.lbmeals.feature_meal.domain.model.Meal

data class MealState(
    val meals: List<Meal> = emptyList(),
)
