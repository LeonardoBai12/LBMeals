package com.example.lbmeals.feature_categories.domain.use_case

import com.example.lbmeals.feature_categories.domain.model.Category
import com.example.lbmeals.feature_categories.domain.repository.CategoryRepository

class GetCategoryUseCase(
    private val repository: CategoryRepository,
) {
    suspend operator fun invoke(): List<Category> {
        return repository.getCategories()
    }
}