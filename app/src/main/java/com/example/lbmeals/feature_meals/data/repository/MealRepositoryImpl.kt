package com.example.lbmeals.feature_meals.data.repository

import com.example.lbmeals.feature_meals.data.data_source.MealService
import com.example.lbmeals.feature_meals.domain.model.Meal
import com.example.lbmeals.feature_meals.domain.repository.MealRepository

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