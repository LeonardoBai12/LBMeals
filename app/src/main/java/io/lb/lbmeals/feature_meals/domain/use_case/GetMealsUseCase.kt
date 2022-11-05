package io.lb.lbmeals.feature_meals.domain.use_case

import io.lb.lbmeals.feature_meals.domain.model.Meal
import io.lb.lbmeals.feature_meals.domain.repository.MealRepository

class GetMealsUseCase(
    private val repository: MealRepository,
) {
    suspend operator fun invoke(category: String): List<Meal> {
        return repository.getMealsByCategory(category)
    }
}