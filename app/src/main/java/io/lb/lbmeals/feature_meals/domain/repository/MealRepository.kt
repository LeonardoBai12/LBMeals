package io.lb.lbmeals.feature_meals.domain.repository

import io.lb.lbmeals.feature_meals.domain.model.Meal

interface MealRepository {
    suspend fun getMealsByCategory(category: String): List<Meal>
}