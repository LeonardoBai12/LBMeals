package io.lb.lbmeals.feature_meals.domain.repository

import io.lb.lbmeals.feature_meals.domain.model.Meal
import io.lb.lbmeals.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * API calls related to meals.
 */
interface MealRepository {
    /**
     * Request a list of meals by category.
     *
     * @param category Category name.
     */
    suspend fun getMealsByCategory(category: String): Flow<Resource<List<Meal>>>
}
