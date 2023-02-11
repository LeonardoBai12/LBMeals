package io.lb.lbmeals.feature_categories.domain.repository

import io.lb.lbmeals.feature_categories.domain.model.Category
import io.lb.lbmeals.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * API calls related to meal categories.
 */
interface CategoryRepository {
    /**
     * Request a list of meal categories.
     */
    suspend fun getCategories(): Flow<Resource<List<Category>>>
}
