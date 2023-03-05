package io.lb.lbmeals.feature_meals.domain.use_case

import io.lb.lbmeals.feature_meals.domain.model.Meal
import io.lb.lbmeals.feature_meals.domain.repository.MealRepository
import io.lb.lbmeals.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Use case to request a single meal and all its details.
 *
 * @property repository API calls related to meal details.
 */
class GetMealDetailsByIdUseCase(
    private val repository: MealRepository,
) {
    suspend operator fun invoke(id: String): Flow<Resource<Meal?>> {
        return repository.getMealDetailsById(id)
    }
}
