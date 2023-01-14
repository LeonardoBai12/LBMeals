package io.lb.lbmeals.feature_meal_details.domain.repository

import io.lb.lbmeals.feature_meals.domain.model.Meal
import io.lb.lbmeals.util.Resource
import kotlinx.coroutines.flow.Flow

interface MealDetailsRepository {
    suspend fun getMealDetailsById(id: String): Flow<Resource<Meal?>>
}