package com.example.lbmeals.feature_meal_details.domain.use_case

import com.example.lbmeals.feature_meal_details.domain.repository.MealDetailsRepository
import com.example.lbmeals.feature_meals.domain.model.Meal

class GetMealDetailsByIdUseCase(
    private val repository: MealDetailsRepository,
) {
    suspend operator fun invoke(id: String): Meal? {
        return repository.getMealDetailsById(id)
    }
}