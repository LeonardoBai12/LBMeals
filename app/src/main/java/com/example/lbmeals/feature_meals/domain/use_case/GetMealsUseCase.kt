package com.example.lbmeals.feature_meals.domain.use_case

import com.example.lbmeals.feature_meals.domain.model.Meal
import com.example.lbmeals.feature_meals.domain.repository.MealRepository

class GetMealsUseCase(
    private val repository: MealRepository,
) {
    suspend operator fun invoke(category: String): List<Meal> {
        return repository.getMealsByCategory(category)
    }
}