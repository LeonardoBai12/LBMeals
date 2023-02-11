package io.lb.lbmeals.feature_meal_details.domain.repository

import io.lb.lbmeals.feature_meals.domain.model.Meal
import io.lb.lbmeals.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * API calls related to meal details.
 */
interface MealDetailsRepository {
    /**
     * Request a single meal.
     *
     * @param id Meal API ID.
     */
    suspend fun getMealDetailsById(id: String): Flow<Resource<Meal?>>
}
