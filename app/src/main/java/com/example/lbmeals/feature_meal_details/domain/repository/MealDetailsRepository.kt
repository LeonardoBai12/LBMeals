package com.example.lbmeals.feature_meal_details.domain.repository

import com.example.lbmeals.feature_meals.domain.model.Meal

interface MealDetailsRepository {
    suspend fun getMealDetailsById(id: String): Meal?
}