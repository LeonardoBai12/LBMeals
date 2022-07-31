package com.example.lbmeals.feature_categories.data.repository

import com.example.lbmeals.feature_categories.data.data_source.CategoryService
import com.example.lbmeals.feature_categories.domain.model.Category
import com.example.lbmeals.feature_categories.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val service: CategoryService
): CategoryRepository {
    override suspend fun getCategories(): List<Category> {
        return service.getCategories().takeIf {
            it.isSuccessful
        }?.let {
            it.body()?.categories
        } ?: emptyList()
    }
}