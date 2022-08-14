package com.example.lbmeals.feature_meal_details.presentation

import com.example.lbmeals.feature_meals.domain.model.Meal

data class MealDetailsState(
    val meal: Meal? = null,
    val loading: Boolean = true,
)