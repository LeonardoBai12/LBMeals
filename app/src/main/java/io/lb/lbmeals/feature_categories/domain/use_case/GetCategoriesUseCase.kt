package io.lb.lbmeals.feature_categories.domain.use_case

import io.lb.lbmeals.feature_categories.domain.model.Category
import io.lb.lbmeals.feature_categories.domain.repository.CategoryRepository

class GetCategoriesUseCase(
    private val repository: CategoryRepository,
) {
    suspend operator fun invoke(): List<Category> {
        return repository.getCategories()
    }
}