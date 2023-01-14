package io.lb.lbmeals.feature_categories.domain.repository

import io.lb.lbmeals.feature_categories.domain.model.Category
import io.lb.lbmeals.util.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories(): Flow<Resource<List<Category>>>
}