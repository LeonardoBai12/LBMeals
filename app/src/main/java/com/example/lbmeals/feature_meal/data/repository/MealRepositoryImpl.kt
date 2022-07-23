package com.example.lbmeals.feature_meal.data.repository

import com.example.lbmeals.feature_meal.data.data_source.MealService
import com.example.lbmeals.feature_meal.domain.model.Meal
import com.example.lbmeals.feature_meal.domain.repository.MealRepository

class MealRepositoryImpl(
    private val service: MealService
): MealRepository {
    override suspend fun getMealsByCategory(category: String): List<Meal> {
        return service.getMealByCategory(category).takeIf {
            it.isSuccessful
        }?.let {
            it.body()?.meals
        } ?: emptyList()
    }
}