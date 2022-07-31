package com.example.lbmeals.feature_categories.domain.repository

import com.example.lbmeals.feature_categories.domain.model.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}