package io.lb.lbmeals.feature_categories.domain.use_case

/**
 * Use cases related to meal categories.
 *
 * @property getCategoriesUseCase Use case to request a list of meal categories.
 */
data class CategoryUseCases(
       val getCategoriesUseCase: GetCategoriesUseCase,
)

