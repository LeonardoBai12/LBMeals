package com.example.lbmeals.feature_meals.domain.repository

import com.example.lbmeals.feature_meals.domain.model.Meal

interface MealRepository {
    suspend fun getMealsByCategory(category: String): List<Meal>
}