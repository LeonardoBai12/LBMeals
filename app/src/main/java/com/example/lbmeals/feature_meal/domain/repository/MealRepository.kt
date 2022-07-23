package com.example.lbmeals.feature_meal.domain.repository

import com.example.lbmeals.feature_meal.domain.model.Meal

interface MealRepository {
    suspend fun getMealsByCategory(category: String): List<Meal>
}