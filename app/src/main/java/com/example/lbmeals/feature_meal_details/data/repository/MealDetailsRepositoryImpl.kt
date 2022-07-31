package com.example.lbmeals.feature_meal_details.data.repository

import com.example.lbmeals.feature_meal_details.data.data_source.MealDetailsService
import com.example.lbmeals.feature_meal_details.domain.repository.MealDetailsRepository
import com.example.lbmeals.feature_meals.domain.model.Meal

class MealDetailsRepositoryImpl(
    private val service: MealDetailsService,
): MealDetailsRepository {
    override suspend fun getMealDetailsById(id: String): Meal? {
        return service.getMealDetailsById(id).takeIf {
            it.isSuccessful
        }?.let {
            it.body()?.meals?.first()
        }
    }
}