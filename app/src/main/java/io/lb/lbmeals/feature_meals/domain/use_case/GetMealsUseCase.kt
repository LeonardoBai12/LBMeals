package io.lb.lbmeals.feature_meals.domain.use_case

import io.lb.lbmeals.feature_meals.domain.model.Meal
import io.lb.lbmeals.feature_meals.domain.repository.MealRepository
import io.lb.lbmeals.util.Resource
import kotlinx.coroutines.flow.Flow

class GetMealsUseCase(
    private val repository: MealRepository,
) {
    suspend operator fun invoke(category: String): Flow<Resource<List<Meal>>> {
        return repository.getMealsByCategory(category)
    }
}
