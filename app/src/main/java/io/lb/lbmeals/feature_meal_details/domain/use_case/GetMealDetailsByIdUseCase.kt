package io.lb.lbmeals.feature_meal_details.domain.use_case

import io.lb.lbmeals.feature_meal_details.domain.repository.MealDetailsRepository
import io.lb.lbmeals.feature_meals.domain.model.Meal

class GetMealDetailsByIdUseCase(
    private val repository: MealDetailsRepository,
) {
    suspend operator fun invoke(id: String): Meal? {
        return repository.getMealDetailsById(id)
    }
}