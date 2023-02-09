package io.lb.lbmeals.feature_categories.domain.use_case

import io.lb.lbmeals.feature_categories.domain.model.Category
import io.lb.lbmeals.feature_categories.domain.repository.CategoryRepository
import io.lb.lbmeals.util.Resource
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(
    private val repository: CategoryRepository,
) {
    suspend operator fun invoke(): Flow<Resource<List<Category>>> {
        return repository.getCategories()
    }
}
