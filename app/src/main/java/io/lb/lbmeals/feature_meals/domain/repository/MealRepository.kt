package io.lb.lbmeals.feature_meals.domain.repository

import io.lb.lbmeals.feature_meals.domain.model.Meal
import io.lb.lbmeals.util.Resource
import kotlinx.coroutines.flow.Flow

interface MealRepository {
    suspend fun getMealsByCategory(category: String): Flow<Resource<List<Meal>>>
}
