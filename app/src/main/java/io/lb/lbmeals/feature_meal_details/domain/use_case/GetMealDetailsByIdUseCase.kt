package io.lb.lbmeals.feature_meal_details.domain.use_case

import io.lb.lbmeals.feature_meal_details.domain.repository.MealDetailsRepository
import io.lb.lbmeals.feature_meals.domain.model.Meal
import io.lb.lbmeals.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Use case to request a single meal.
 *
 * @property repository API calls related to meal details.
 */
class GetMealDetailsByIdUseCase(
    private val repository: MealDetailsRepository,
) {
    suspend operator fun invoke(id: String): Flow<Resource<Meal?>> {
        return repository.getMealDetailsById(id)
    }
}
