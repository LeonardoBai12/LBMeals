package io.lb.lbmeals.feature_categories.domain.repository

import io.lb.lbmeals.feature_categories.domain.model.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}