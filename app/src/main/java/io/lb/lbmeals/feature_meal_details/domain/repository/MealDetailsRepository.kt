package io.lb.lbmeals.feature_meal_details.domain.repository

import io.lb.lbmeals.feature_meals.domain.model.Meal

interface MealDetailsRepository {
    suspend fun getMealDetailsById(id: String): Meal?
}